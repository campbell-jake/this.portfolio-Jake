package com.techelevator.model.campground;

import java.math.BigDecimal;

public class Campground {

	/************************************************************************************************************************************
	 * Instance Variables
	 ***********************************************************************************************************************************/
	
	private int campgroundId;
	private int parkId;
	private String campgroundName;
	private String dateOpen;
	private String dateClose;
	private BigDecimal dailyFee;
	
	/************************************************************************************************************************************
	 * Getters
	 ***********************************************************************************************************************************/

	public int getCampgroundId() {
		return campgroundId;
	}
	public int getParkId() {
		return parkId;
	}
	public String getCampgroundName() {
		return campgroundName;
	}
	public String getDateOpen() {
		return dateOpen;
	}
	public String getDateClose() {
		return dateClose;
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}

	/************************************************************************************************************************************
	 * Setters
	 ***********************************************************************************************************************************/

	public void setCampgroundId(int campgroundId) {
		this.campgroundId = campgroundId;
	}
	public void setParkId(int parkId) {
		this.parkId = parkId;
	}
	public void setCampgroundName(String campgroundName) {
		this.campgroundName = campgroundName;
	}
	public void setDateOpen(String dateOpen) {
		this.dateOpen = dateOpen;
	}
	public void setDateClose(String dateClose) {
		this.dateClose = dateClose;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
	
	/************************************************************************************************************************************
	 * Methods
	 ***********************************************************************************************************************************/
	
	public String toString() {
		return String.format(campgroundName + " \t" + dateOpen + "   " + dateClose + "    $" + dailyFee);
	}
		
}
