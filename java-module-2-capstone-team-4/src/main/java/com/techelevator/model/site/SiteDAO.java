package com.techelevator.model.site;

import java.util.List;

public interface SiteDAO {

	/**
	 * Get all sites from the campground database.
	 * 
	 * @return all sites as Site objects in a List
	 */
	
	public List<Site> getAvailableSitesForReservationRequest(int campgroundId, String fromDate, String toDate, int fromMonth, int toMonth);
	
	public int getSiteIdFromSiteNumber(int siteNumber, int campgroundId);
}
