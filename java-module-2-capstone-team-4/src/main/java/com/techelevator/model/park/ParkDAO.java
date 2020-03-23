package com.techelevator.model.park;

import java.util.List;


public interface ParkDAO {
	
	/**
	 * Get all parks from the campground database.
	 * 
	 * @return all parks as Park objects in a List
	 */
	
	public List<Park> getAllParks();
	
	public Park getParkByParkName(String selectedPark);
	
	public int getParkIdByParkName(String selectedPark);

}
