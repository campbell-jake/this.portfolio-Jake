package com.techelevator.model.reservation;

import java.time.LocalDate;

public class Reservation {

	/************************************************************************************************************************************
	 * Instance Variables
	 ***********************************************************************************************************************************/
	
	private int reservationId;
	private int siteId;
	private String reservationName;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate bookedDate;
	
	/************************************************************************************************************************************
	 * Getters
	 ***********************************************************************************************************************************/
	
	public int getReservationId() {
		return reservationId;
	}
	public int getSiteId() {
		return siteId;
	}
	public String getReservationName() {
		return reservationName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public LocalDate getBookedDate() {
		return bookedDate;
	}
	
	/************************************************************************************************************************************
	 * Setters
	 ***********************************************************************************************************************************/
	
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public void setBookedDate(LocalDate bookedDate) {
		this.bookedDate = bookedDate;
	}
	
	/************************************************************************************************************************************
	 * Methods
	 ***********************************************************************************************************************************/
	
	public String toString() {
		
		return String.format(reservationName);
	}

}
