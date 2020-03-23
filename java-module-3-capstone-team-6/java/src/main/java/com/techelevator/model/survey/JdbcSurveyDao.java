package com.techelevator.model.survey;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcSurveyDao implements SurveyDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveSurveyInput(String parkCode, String emailAddress, String state, String activityLevel) {
		
		String sqlSaveSurveyInput = "insert into survey_result(parkcode, emailaddress, state, activitylevel) " + 
													   "values(?, ?, ?, ?)";
		
		jdbcTemplate.update(sqlSaveSurveyInput, parkCode, emailAddress, state, activityLevel);
	}

	@Override
	public List<String[]> getAllSurveys() {
		
		List<String[]> surveys = new ArrayList<String[]>();
		
		String sqlGetAllSurveys = "select park.parkname as parkname, count(*) as total, park.parkcode as parkcode " +
								"from survey_result " +
								"inner join park on park.parkcode = survey_result.parkcode " +
								"group by park.parkname, park.parkcode " +
								"order by total desc, park.parkname asc";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllSurveys); 
		
		
		while (results.next()) {
			String[] theResults = new String[3];
			theResults[0] = results.getString("parkname");
			theResults[1] = results.getString("total");
			theResults[2] = results.getString("parkcode");
			surveys.add(theResults);
		}
		
		return surveys;
	}
}
