package com.techelevator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.campground.*;
import com.techelevator.model.park.*;
import com.techelevator.model.reservation.*;
import com.techelevator.model.site.*;
import com.techelevator.view.*;

public class CampgroundApp {
	
	/****************************************************************************************************
	 * This is the Campground Reservation system application program
	 * 
	 * Any and all user interactions should be placed in the CampgroundUI class 
	 *     which is in the com.techelevator.view package.
	 *     
	 * This application program should instantiate a CampgroundUI object 
	 *      and use its methods to interact with the user.
	 *      
	 * This application program should contain no user interactions.
	 * 
	 * Any and all database accesses should be placed in the appropriate
	 *     com.techelevator.model.tablename package component
	 *     
	 * This application program should instantiate DAO objects and use the methods
	 *     in the DAO to interact with the database tables.   
	 *     
	 * There should be no SQL in this application program
	 *   
	 ***************************************************************************************************/
	
	public static void main(String[] args) {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundUI userInterface = new CampgroundUI();  // Object to manage user interactions;
		                                                  // Feel free to change the name
		
		/****************************************************************************************************
		 * Instantiate any DAOs you will be using here
		 ***************************************************************************************************/

		JdbcParkDAO               parkDao = new JdbcParkDAO(dataSource);
		JdbcCampgroundDAO   campgroundDao = new JdbcCampgroundDAO(dataSource);
		JdbcReservationDAO reservationDao = new JdbcReservationDAO(dataSource);
		JdbcSiteDAO               siteDao = new JdbcSiteDAO(dataSource);
		
		/****************************************************************************************************
		 * Define Menu Options
		 ***************************************************************************************************/

		final String VIEW_CAMPGROUNDS  = "View Campgrounds";
		final String SEARCH_FOR_AVAILABLE_RESERVATION = "Search for Available Reservation";
		final String RETURN_TO_PREVIOUS_SCREEN = "Return to Previous Screen";
		
		final String [] SELECT_A_COMMAND = {VIEW_CAMPGROUNDS,
											SEARCH_FOR_AVAILABLE_RESERVATION,
											RETURN_TO_PREVIOUS_SCREEN};
	
 		/****************************************************************************************************
		 * Your application programming logic goes here
		 ***************************************************************************************************/
		
			userInterface.displayAGreeting();

			boolean shouldProcess = true;
		while (shouldProcess) {

			List<Park> parks = parkDao.getAllParks();      // get a list of parks to display as menu options to the user
			String selectedPark = userInterface.displayAllParksAndPickOne(parks);    // return the park name the user selected as a menu option
			if (selectedPark.equals("Quit")) {
				userInterface.endGracefully();

				return;    // user selected "Quit", so exit the program
			}

			Park parkFromSelectedName = parkDao.getParkByParkName(selectedPark);  
			userInterface.displayParkInformation(parkFromSelectedName);   

			boolean shouldProcessCampgrounds = true;    // the loop variable
			while (shouldProcessCampgrounds) {          // user remains in the "select a command" menu while true

				String choice = userInterface.selectACommand(SELECT_A_COMMAND);  // store the user's option as the switch variable

				int parkId = parkDao.getParkIdByParkName(selectedPark);
				List<Campground> campgroundsFromParkId = campgroundDao.getCampgroundsByParkId(parkId);

				switch (choice) {         
				case VIEW_CAMPGROUNDS:  // menu option "View Campgrounds"
	
					userInterface.displayCampgroundsForSelectedPark(campgroundsFromParkId, selectedPark);
					break;
				case SEARCH_FOR_AVAILABLE_RESERVATION:      // menu option "Search for Available Reservation"
					
					String[] reservationRequest = null;     // initialize the loop variable condition
					int campgroundChoice = 0;
					while (reservationRequest == null) {    // loop while reservation request from user is null
						
						reservationRequest = userInterface.getReservationRequestFromUser(campgroundsFromParkId);
						if (reservationRequest != null) {               // if the user entered a value
							if (reservationRequest[0].equals("0")) {    // if user entered "0" to "Cancel"
								break;                                  // return user to "Select a command" menu
							}
							campgroundChoice = Integer.parseInt(reservationRequest[0]);  // the first String in the array is the campground option the user chose to make a reservation at
							if (campgroundChoice > campgroundsFromParkId.size()) {       // if user chooses a campground option that is not listed
								userInterface.displayInvalidOptionSelectMessage(campgroundChoice);
								reservationRequest = null;   // set reservationRequest to null so we can loop again and get a new valid request
								campgroundChoice = 0;
							}
						}
					}
					if(campgroundChoice == 0) {   // if user entered "0" to "Cancel"
						break;                    // return user to "Select a command" menu
					}
					Campground userCampground = campgroundsFromParkId.get(campgroundChoice - 1);  // get Campground object from Campground arrayList corresponding to user's choice (index position is user's choice - 1) 
					int campgroundId = userCampground.getCampgroundId();

					String fromDate = reservationRequest[1];              // index position 1 is the user's desired arrival date
					String toDate = reservationRequest[2];                // index position 2 is the user's desired departure date
					String[] fromDateArray = fromDate.split("-");         // split user's input into [yyyy, mm, dd]
					int fromMonth = Integer.parseInt(fromDateArray[1]);   // convert mm to int for campsite reservation SQL query – checking if campground is open during this month
					String[] toDateArray = toDate.split("-");
					int toMonth = Integer.parseInt(toDateArray[1]);       // convert mm to int for campsite reservation SQL query – checking if campground is open during this month

					List<Site> sites = siteDao.getAvailableSitesForReservationRequest(campgroundId, fromDate, toDate,
							fromMonth, toMonth);

					LocalDate startDate = LocalDate.parse(fromDate);      
					LocalDate endDate = LocalDate.parse(toDate);
					long lengthOfStay = ChronoUnit.DAYS.between(startDate, endDate);  // calculate the length of stay to use in total cost calculation

					BigDecimal dailyFee = userCampground.getDailyFee();
					BigDecimal totalCost = dailyFee.multiply(BigDecimal.valueOf(lengthOfStay));
					
					if(sites.size() == 0) {  // processing if the user's reservation can't be accommodated due to site being closed or booked
						String altDateRangeAnswer = userInterface.getAlternateReservationRequestFromUser();
						
						if(altDateRangeAnswer.equals("Y")) {   // the user wants to try an alternative date
							break;                             // loop back to the menu options for "select a command"
							
						}
						if(altDateRangeAnswer.equals("N")) {   // the user does not want to try an alternative date
							shouldProcessCampgrounds = false;  // loop variable set to false to return to previous menu
							break;
						}
						
					}
					if(sites.size() > 0){    // if the query returned available sites
					userInterface.displayAvailableSiteInformation(sites, totalCost);

					String[] reservationDetails = userInterface.userReservationDetails();  // index position 0 is site to book, index position 1 is reservation name
					if (reservationDetails[0].equals("0")) {                               // if user inputs 0 to cancel reservation
						break;
					} else {
						int siteNumber = Integer.parseInt(reservationDetails[0]);          // use value stored in siteNumber in SQL query to get siteId for reservation creation
						String reservationName = reservationDetails[1];
						int siteId = siteDao.getSiteIdFromSiteNumber(siteNumber, campgroundId);
						Reservation userReservation = reservationDao.createReservation(siteId, reservationName,
								fromDate, toDate);
						int reservationId = userReservation.getReservationId();
						userInterface.displayReservationIdToUser(reservationId);
					}
					}
					shouldProcessCampgrounds = false;   // loop variable set to false to return to previous menu
					break;
				case RETURN_TO_PREVIOUS_SCREEN:         // menu option "Return to Previous Screen"
					shouldProcessCampgrounds = false;   // loop variable set to false to return to previous menu
					break;
				}

			}

		}
	}

}

