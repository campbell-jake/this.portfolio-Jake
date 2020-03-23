package com.techelevator.model.campground;

import java.util.List;

public interface CampgroundDAO {

	/**
	 * Get all campgrounds from the campground database.
	 * 
	 * @return all campgrounds as Campground objects in a List
	 */
	
	public List<Campground> getCampgroundsByParkId(int parkId);
	

	
	
	
}
