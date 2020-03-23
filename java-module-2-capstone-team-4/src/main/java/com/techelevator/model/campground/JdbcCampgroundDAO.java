package com.techelevator.model.campground;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JdbcCampgroundDAO implements CampgroundDAO {

	private JdbcTemplate jdbcTemplate;

	public JdbcCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
		
	@Override
	public List<Campground> getCampgroundsByParkId(int parkId) {
		
		List<Campground> campgrounds = new ArrayList<Campground>();

		String sqlGetCampgroundsByParkId = "select campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee " +
									       "from campground " +
									       "where park_id = " + parkId;

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetCampgroundsByParkId);

		while (results.next()) {
			Campground theCampground = mapRowToCampground(results);
			campgrounds.add(theCampground);
		}

		return campgrounds;
	}

	private Campground mapRowToCampground(SqlRowSet results) {

		Campground theCampground = new Campground();

		theCampground.setCampgroundId(results.getInt("campground_id"));
		theCampground.setParkId(results.getInt("park_id"));
		theCampground.setCampgroundName(results.getString("name"));
		theCampground.setDateOpen(results.getString("open_from_mm"));
		theCampground.setDateClose(results.getString("open_to_mm"));
		theCampground.setDailyFee(results.getBigDecimal("daily_fee"));

		return theCampground;

	}
}
