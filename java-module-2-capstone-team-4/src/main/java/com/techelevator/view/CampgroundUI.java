package com.techelevator.view;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.techelevator.model.campground.Campground;
import com.techelevator.model.park.Park;
import com.techelevator.model.site.Site;

public class CampgroundUI {
	
	/*******************************************************************************************************
	 * This is the CampgroundUI class
	 * 
	 * All user interactions should be coded here
	 * 
	 * The application program will instantiate an object of this class and use the object to interact
	 * with the user.
	 * 
	 * Any data required from the user for the application will be acquired by methods of this class
	 * and sent back to the user either as the return value from the method or in an object returned
	 * from the method.
	 * 
	 * Any messages the application programmer wishes to display to the user may be sent to methods of
	 * this class as variables or objects.  Any messaging method may also have "canned" messages the
	 * user may request by a message id.
	 * 
	 *******************************************************************************************************/
	
	
	/*******************************************************************************************************
	 * Menu class object
	 * 
	 * Use this Menu object for ALL Menu choices presented to the user
	 * 
	 * This is the same Menu class discussed in module 1 and made available in the module 1 capstone
	 * 
	 * 
	 ******************************************************************************************************/
	
	Menu CampMenu = new Menu(System.in, System.out);  // Define menu for keyboard input and screen output
	Scanner theKeyboard = new Scanner(System.in);

	/*******************************************************************************************************
	 * Class member variables, objects, constants and methods should be coded here. 
	 ******************************************************************************************************/
	
	public void displayAGreeting() {
		System.out.println("\n*************************************");
		System.out.println("   Welcome to the National Park");
		System.out.println(" Campsite Reservation Application!");
		System.out.println("***************************************");
	}
	
	public String displayAllParksAndPickOne(List<Park> parks) {
		
		String[] parkOptions = new String[parks.size()+1];
	
		int i = 0;
		for (Park aPark : parks) {
			
			String parkName = aPark.getParkName();
			parkOptions[i] = parkName;
			i++;
		}
		
		parkOptions[parks.size()] = "Quit";
	    
		System.out.println();
		System.out.println("Select a Park for Further Details");
		String choice = (String)CampMenu.getChoiceFromOptions(parkOptions);
		
		return choice;
		
		}	
	
	public void displayParkInformation(Park parkFromSelectedName) {
		
		
		System.out.println();
		System.out.println(parkFromSelectedName.getParkName() + " National Park");
		System.out.println("Location: \t " + parkFromSelectedName.getParkLocation());
		System.out.println("Established: \t " + DateTimeFormatter.ofPattern("MM/dd/yyyy").format(parkFromSelectedName.getEstablishedDate()));
		System.out.println("Area: \t\t " + NumberFormat.getInstance().format(parkFromSelectedName.getParkArea()) + " sq km");
		System.out.println("Annual Visitors: " + NumberFormat.getInstance().format(parkFromSelectedName.getVisitorCount()));
		System.out.println();
		System.out.println(parkFromSelectedName.getParkDescription());
		
	}
	
	public void displayCampgroundsForSelectedPark(List<Campground> campgroundsFromParkId, String selectedPark) {

		System.out.println();
		System.out.println(selectedPark + " National Park Campgrounds");
		System.out.println();
		System.out.print(String.format("%-5s", " "));
		System.out.print(String.format("%-35s", "Name"));
		System.out.print(String.format("%-15s", "Open"));
		System.out.print(String.format("%-15s", "Close"));
		System.out.print(String.format("%-15s", "Daily Fee"));
		System.out.println();

		for (int i = 0; i < campgroundsFromParkId.size(); i++) {
			Campground aCampground = campgroundsFromParkId.get(i);
			System.out.print(String.format("%-5s", i+1 + ")"));
			System.out.print(String.format("%-35s", aCampground.getCampgroundName()));
			System.out.print(String.format("%-15s", DateFormatSymbols.getInstance().getMonths()[Integer.parseInt(aCampground.getDateOpen())-1]));           
			System.out.print(String.format("%-15s", DateFormatSymbols.getInstance().getMonths()[Integer.parseInt(aCampground.getDateClose())-1]));
			System.out.print(String.format("%-15s", "$" + new DecimalFormat("0.00").format(aCampground.getDailyFee())));
			System.out.println();
			
		}
	}
	
	
	public String selectACommand(String[] SELECT_A_COMMAND) {
		System.out.println();
		System.out.println("Select a Command");
	
		String choice = (String)CampMenu.getChoiceFromOptions(SELECT_A_COMMAND);
		
		return choice;
		
		}
		
	public String[] getReservationRequestFromUser(List<Campground> campgroundsFromParkId) {
		
		System.out.println();
		System.out.println("Search for Campground Reservation");
		System.out.println();
		System.out.print(String.format("%-5s", " "));
		System.out.print(String.format("%-35s", "Name"));
		System.out.print(String.format("%-15s", "Open"));
		System.out.print(String.format("%-15s", "Close"));
		System.out.print(String.format("%-15s", "Daily Fee"));
		System.out.println();

		for (int i = 0; i < campgroundsFromParkId.size(); i++) {
			Campground aCampground = campgroundsFromParkId.get(i);
			System.out.print(String.format("%-5s", i+1 + ")"));
			System.out.print(String.format("%-35s", aCampground.getCampgroundName()));
			System.out.print(String.format("%-15s", DateFormatSymbols.getInstance().getMonths()[Integer.parseInt(aCampground.getDateOpen())-1]));           
			System.out.print(String.format("%-15s", DateFormatSymbols.getInstance().getMonths()[Integer.parseInt(aCampground.getDateClose())-1]));
			System.out.print(String.format("%-15s", "$" + new DecimalFormat("0.00").format(aCampground.getDailyFee())));
			System.out.println();
			
		}
			
		String[] reservationRequest = new String[3];
		
		System.out.println();
		System.out.println("Which campground (enter the number or 0 to cancel)");
		String campgroundChoice = theKeyboard.nextLine();
		
		try {
		if(Integer.parseInt(campgroundChoice) == 0) {
			reservationRequest[0] = "0";
			reservationRequest[1] = "0";
			reservationRequest[2] = "0";	
		}
		else {
			System.out.println("What is the arrival date (yyyy-mm-dd)?");
			String fromDate = theKeyboard.nextLine();
			System.out.println("What is the departure date (yyyy-mm-dd)?");
			String toDate = theKeyboard.nextLine();
			
			reservationRequest[0] = campgroundChoice;
			reservationRequest[1] = fromDate;
			reservationRequest[2] = toDate;
			}
			
			
			return reservationRequest;
		}
		catch(NumberFormatException exceptionObject ){
			return null;
		}
		
	}
	
	public String getAlternateReservationRequestFromUser() {
		
		System.out.println();
		System.out.println("There are no campsites available for the requested Date Range. ");
		System.out.println("Would you like to try an alternative date range? (Y/N)");
		String altDateRangeAnswer = theKeyboard.nextLine();
		
		return altDateRangeAnswer;
		
	}
		
	public void displayAvailableSiteInformation(List<Site> sites, BigDecimal totalCost) {
		
		System.out.println();
		System.out.println("Results Matching Your Criteria");
		System.out.println();
		System.out.print(String.format("%-20s", "Site No."));
		System.out.print(String.format("%-20s", "Max Occup."));
		System.out.print(String.format("%-20s", "Accessible?"));
		System.out.print(String.format("%-20s", "Max RV Length"));
		System.out.print(String.format("%-20s", "Utility"));
		System.out.print(String.format("%-20s", "Total Cost"));
		System.out.println();

		for (Site aSite : sites) {
			
			String accessible = aSite.isAccessible() == true ? "Yes" : "No";
			String rvLength = aSite.getMaxRVLength() == 0 ? "N/A" : String.valueOf(aSite.getMaxRVLength());
			String utility = aSite.isHasUtilities() == true ? "Yes" : "N/A";
			
			
			System.out.print(String.format("%-20s", aSite.getSiteNumber()));
			System.out.print(String.format("%-20s",aSite.getMaxOccupancy()));
			System.out.print(String.format("%-20s", accessible));
			System.out.print(String.format("%-20s", rvLength));
			System.out.print(String.format("%-20s", utility));
			System.out.print(String.format("%-20s", "$" + new DecimalFormat("0.00").format(totalCost)));
			System.out.println();
		}
	}
	
	public String[] userReservationDetails() {
		
		System.out.println();
		System.out.println("Which site should be reserved (enter 0 to cancel)?");
		String siteNumber = theKeyboard.nextLine();
		String[] reservationDetails = new String[2];

		if(Integer.parseInt(siteNumber) == 0) {
			reservationDetails[0] = "0";
			reservationDetails[1] = "0";	
		}
		else {
		System.out.println("What name should the reservation be under?");
		String reservationName = theKeyboard.nextLine();
		reservationDetails[0] = siteNumber;
		reservationDetails[1] = reservationName;
		}
		return reservationDetails;

	}
	
	public void displayReservationIdToUser (int reservationId) {
		System.out.println();
		System.out.println("The reservation has been made and the confirmation id is " + reservationId);
	}
	
	public void displayInvalidOptionSelectMessage(int campgroundChoice) {
		
		System.out.println();
		System.out.println(campgroundChoice + ") is not a valid select. Please select one of the displayed Numbers");
		
	}
		
	public void endGracefully() {
		System.out.println();
		System.out.println("******************************************");
		System.out.println("  Thank you for using the National Park");
		System.out.println("    Campsite Reservation Application!");
		System.out.println("       We welcome your next visit!");
		System.out.println("******************************************");
	}
}

