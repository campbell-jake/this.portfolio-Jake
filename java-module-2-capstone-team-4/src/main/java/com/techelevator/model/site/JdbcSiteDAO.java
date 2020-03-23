package com.techelevator.model.site;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcSiteDAO implements SiteDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JdbcSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Site> getAvailableSitesForReservationRequest(int campgroundId, String fromDate, String toDate, int fromMonth, int toMonth){

		
		List<Site> sites = new ArrayList<Site>();
		
		String sqlSelectSiteByReservationRequest = "select * " + 
												   "from site " + 
												   "where site_id in (select site.site_id from site where site.site_id not in " + 
												   "(select reservation.site_id " + 
												   "from reservation " + 
												   "where '" + fromDate + "' < to_date and '" + toDate + "' > from_date)) " + 
												   "and campground_id in (select site.campground_id from site where site.campground_id in " + 
												   "(select campground_id " + 
												   "from campground " + 
												   "where "+ fromMonth + " >= cast(open_from_mm as Integer) and " + toMonth + " <= cast(open_to_mm as Integer))) " + 
												   "and campground_id = " + campgroundId +
												   "limit 5";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectSiteByReservationRequest);
		
		while(result.next()) {
			Site theSite = mapRowToSite(result);
			sites.add(theSite);
		}
		
		return sites;
		
		
	}
	
	public int getSiteIdFromSiteNumber(int siteNumber, int campgroundId) {
		
		int siteId = 0;
		String sqlGetSiteIdFromSiteNumber = "select site_id " +
											"from site " +
											"where site_number = ? and campground_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetSiteIdFromSiteNumber, siteNumber, campgroundId);
		
		while(result.next()) {
			siteId = result.getInt("site_id");
			
		}
		return siteId;
	}

	private Site mapRowToSite(SqlRowSet results) {

		Site theSite = new Site();

		theSite.setSiteId(results.getInt("site_id"));
		theSite.setCampgroundId(results.getInt("campground_id"));
		theSite.setSiteNumber(results.getInt("site_number"));
		theSite.setMaxOccupancy(results.getInt("max_occupancy"));
		theSite.setAccessible(results.getBoolean("accessible"));
		theSite.setMaxRVLength(results.getInt("max_rv_length"));
		theSite.setHasUtilities(results.getBoolean("utilities"));

		return theSite;

	}
	
}
