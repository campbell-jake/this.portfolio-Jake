package com.techelevator.model.park;

import java.time.LocalDate;

public class Park {
	
	/************************************************************************************************************************************
	 * Instance Variables
	 ***********************************************************************************************************************************/
	
	private int parkId;
	private String parkName;
	private String parkLocation;
	private LocalDate establishedDate;
	private int parkArea;
	private int visitorCount;
	private String parkDescription;
	
	
	/************************************************************************************************************************************
	 * Getters
	 ***********************************************************************************************************************************/
	
	public int getParkId() {
		return parkId;
	}
	public String getParkName() {
		return parkName;
	}
	public String getParkLocation() {
		return parkLocation;
	}
	public LocalDate getEstablishedDate() {
		return establishedDate;
	}
	public int getParkArea() {
		return parkArea;
	}
	public int getVisitorCount() {
		return visitorCount;
	}
	public String getParkDescription() {
		return parkDescription;
	}
	
	/************************************************************************************************************************************
	 * Setters
	 ***********************************************************************************************************************************/
	
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public void setParkLocation(String parkLocation) {
		this.parkLocation = parkLocation;
	}
	public void setEstablishedDate(LocalDate establishedDate) {
		this.establishedDate = establishedDate;
	}
	public void setParkArea(int parkArea) {
		this.parkArea = parkArea;
	}
	public void setVisitorCount(int visitorCount) {
		this.visitorCount = visitorCount;
	}
	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	
	/************************************************************************************************************************************
	 * Methods
	 ***********************************************************************************************************************************/
	
	public String toString() {
		return String.format(parkName + " National Park" + "\n" +
							 "Location: \t " + parkLocation + "\n" +
							 "Established: \t " + establishedDate + "\n" +
							 "Area: \t\t " + parkArea + " sq km" + "\n" +
							 "Annual Visitors: " + visitorCount + "\n" + "\n" +
							 parkDescription);
	}
	
	

}
