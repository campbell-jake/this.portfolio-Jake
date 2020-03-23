package com.techelevator.model.park;

import java.util.List;

public interface ParkDao {

	public List<Park> getAllParks();
	
	public Park getParkByParkCode(String parkCode);
	
	public List<String> getAllParkNames();
	
}
