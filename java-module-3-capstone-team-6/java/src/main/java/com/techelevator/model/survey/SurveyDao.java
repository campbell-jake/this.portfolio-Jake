package com.techelevator.model.survey;

import java.util.List;

public interface SurveyDao {
	
	public void saveSurveyInput(String parkCode, String emailAddress, String state, String activityLevel);
	
	public List<String[]> getAllSurveys();

}
