package com.techelevator.model.site;

public class Site {
	
	/************************************************************************************************************************************
	 * Instance Variables
	 ***********************************************************************************************************************************/
	
	private int siteId;
	private int campgroundId;
	private int siteNumber;
	private int maxOccupancy;
	private boolean isAccessible;
	private int maxRVLength;
	private boolean hasUtilities;
	
	/************************************************************************************************************************************
	 * Getters
	 ***********************************************************************************************************************************/
	
	public int getSiteId() {
		return siteId;
	}
	public int getCampgroundId() {
		return campgroundId;
	}
	public int getSiteNumber() {
		return siteNumber;
	}
	public int getMaxOccupancy() {
		return maxOccupancy;
	}
	public boolean isAccessible() {
		return isAccessible;
	}
	public int getMaxRVLength() {
		return maxRVLength;
	}
	public boolean isHasUtilities() {
		return hasUtilities;
	}
	
	/************************************************************************************************************************************
	 * Setters
	 ***********************************************************************************************************************************/
	
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	public void setSiteNumber(int siteNumber) {
		this.siteNumber = siteNumber;
	}
	public void setMaxOccupancy(int maxOccupancy) {
		this.maxOccupancy = maxOccupancy;
	}
	public void setAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
	public void setMaxRVLength(int maxRVLength) {
		this.maxRVLength = maxRVLength;
	}
	public void setHasUtilities(boolean hasUtilities) {
		this.hasUtilities = hasUtilities;
	}
	
	/************************************************************************************************************************************
	 * Methods
	 ***********************************************************************************************************************************/
	
	public String toString() {
		return String.format(siteNumber + " " + maxOccupancy + " " + isAccessible + " " + maxRVLength + " " + hasUtilities);
	}
		
}
