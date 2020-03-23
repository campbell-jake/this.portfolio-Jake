package com.techelevator.model.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JdbcParkDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;

	public JdbcParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {

		List<Park> parks = new ArrayList<Park>();

		String sqlGetAllParks = "select park_id, name, location, establish_date, area, visitors, description "
									+ "from park "
									+ "order by name asc";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllParks);

		while (results.next()) {
			Park thePark = mapRowToPark(results);
			parks.add(thePark);
		}

		return parks;

	}

	@Override
	public Park getParkByParkName(String selectedPark) {
		
		String sqlGetParkByParkName = "select park_id, name, location, establish_date, area, visitors, description "
									+ "from park "
									+ "where name = '" + selectedPark + "'";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkByParkName);
		
		Park thePark = new Park();
		while (results.next()) {
			thePark = mapRowToPark(results);
			
		}
		return thePark;
	}
	
	@Override
	public int getParkIdByParkName(String selectedPark) {
		
		String sqlGetParkIdByParkName = "select park_id "
									  + "from park "
									  + "where name = '" + selectedPark + "'";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParkIdByParkName);
		
		int parkId = 0;
		while (results.next()) {
			parkId = results.getInt("park_id");
			
		}
		return parkId;
	}
	
	
	private Park mapRowToPark(SqlRowSet results) {

		Park thePark = new Park();

		thePark.setParkId(results.getInt("park_id"));
		thePark.setParkName(results.getString("name"));
		thePark.setParkLocation(results.getString("location"));
		thePark.setEstablishedDate(results.getDate("establish_date").toLocalDate());
		thePark.setParkArea(results.getInt("area"));
		thePark.setVisitorCount(results.getInt("visitors"));
		thePark.setParkDescription(results.getString("description"));
		
		return thePark;

	}
}