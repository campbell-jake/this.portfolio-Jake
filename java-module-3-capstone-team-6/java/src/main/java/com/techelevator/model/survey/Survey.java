package com.techelevator.model.survey;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Survey {
	
	/************************************************************************************************************************************
	 * Instance Variables
	 ***********************************************************************************************************************************/

	private int surveyId;
	private String parkCode;
	
	@NotBlank(message="Email Address is a required field.")
	@Email(message = "Please provide a valid email address.")
	private String emailAddress;
	
	private String state;
	private String activityLevel;

	/************************************************************************************************************************************
	 * Getters
	 ***********************************************************************************************************************************/

	public int getSurveyId() {
		return surveyId;
	}
	public String getParkCode() {
		return parkCode;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getState() {
		return state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	/************************************************************************************************************************************
	 * Setters
	 ***********************************************************************************************************************************/
	
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
}
