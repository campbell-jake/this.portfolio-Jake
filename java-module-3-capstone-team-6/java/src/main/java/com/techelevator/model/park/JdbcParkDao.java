package com.techelevator.model.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDao implements ParkDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {

		List<Park> parks = new ArrayList<Park>();

		String sqlGetAllParks = "select * from park order by parkname";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);

		while (results.next()) {
			Park aPark = mapRowToPark(results);
			parks.add(aPark);
		}

		return parks;
	}

	@Override
	public Park getParkByParkCode(String parkCode) {

		Park aPark = new Park();
		
		String sqlGetParkByParkCode = "select * from park where parkcode = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkByParkCode, parkCode);
		
		while(results.next()) {
			aPark = mapRowToPark(results);
		}
		
		return aPark;
	}
	
	private Park mapRowToPark(SqlRowSet results) {

		Park aPark = new Park();

		aPark.setParkCode(results.getString("parkcode"));
		aPark.setParkName(results.getString("parkname"));
		aPark.setState(results.getString("state"));
		aPark.setAcreage(results.getInt("acreage"));
		aPark.setElevation(results.getInt("elevationinfeet"));
		aPark.setMilesOfTrails(results.getFloat("milesoftrail"));
		aPark.setNumberOfCampsites(results.getInt("numberofcampsites"));
		aPark.setClimate(results.getString("climate"));
		aPark.setYearFounded(results.getInt("yearfounded"));
		aPark.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		aPark.setInspirationalQuote(results.getString("inspirationalquote"));
		aPark.setQuoteSource(results.getString("inspirationalquotesource"));
		aPark.setParkDescription(results.getString("parkdescription"));
		aPark.setEntryFee(results.getInt("entryfee"));
		aPark.setNumberAnimalSpecies(results.getInt("numberofanimalspecies"));

		return aPark;

	}
}
