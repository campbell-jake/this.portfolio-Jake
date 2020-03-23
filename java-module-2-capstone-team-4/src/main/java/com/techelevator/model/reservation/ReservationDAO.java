package com.techelevator.model.reservation;

import java.util.List;

public interface ReservationDAO {

	/**
	 * Get all reservations from the campground database.
	 * 
	 * @return all reservations as Reservation objects in a List
	 */
	
	public Reservation createReservation(int siteId, String reservationName, String fromDate, String toDate);	
	
	
}
