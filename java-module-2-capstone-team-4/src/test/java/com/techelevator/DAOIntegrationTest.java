package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.campground.Campground;
import com.techelevator.model.campground.JdbcCampgroundDAO;
import com.techelevator.model.park.JdbcParkDAO;
import com.techelevator.model.park.Park;
import com.techelevator.model.reservation.JdbcReservationDAO;
import com.techelevator.model.reservation.Reservation;
import com.techelevator.model.site.JdbcSiteDAO;
import com.techelevator.model.site.Site;

public class DAOIntegrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	
	private JdbcCampgroundDAO testCampgroundDao;
	private JdbcReservationDAO testReservationDao;
	private JdbcParkDAO testParkDao;
	private JdbcSiteDAO testSiteDao;


	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
	}

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	/* Before all tests run, this method will instantiate the Dao objects with methods to test */
	@Before
	public void setup() {
		testCampgroundDao = new JdbcCampgroundDAO(dataSource);
		testReservationDao = new JdbcReservationDAO(dataSource);
		testParkDao = new JdbcParkDAO(dataSource);
		testSiteDao = new JdbcSiteDAO(dataSource);

	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void test_Get_Campgrounds_By_ParkId() {
		List<Campground> campgrounds = testCampgroundDao.getCampgroundsByParkId(1);
		assertNotNull(campgrounds);
		assertEquals(3, campgrounds.size());
	}
		
	@Test
	public void test_Create_Reservation() {
		Reservation theReservation = testReservationDao.createReservation(1, "Jake", "2020-02-23", "2020-02-27");
		assertEquals("Jake", theReservation.getReservationName());
	}
	
	@Test
	public void test_Get_All_Parks() {
		List<Park> theParks = testParkDao.getAllParks();
		assertNotNull(theParks);
		assertEquals(3, theParks.size());
	}
	
	@Test
	public void test_Get_Park_By_Park_Name() {
		Park thePark = testParkDao.getParkByParkName("Arches");
		assertEquals("Arches", thePark.getParkName());
	}
	
	@Test
	public void test_Get_ParkId_By_Park_Name() {
		int parkId = testParkDao.getParkIdByParkName("Arches");
		assertEquals(2, parkId);
	}
	
	@Test
	public void test_Get_Available_Sites_For_Reservation_Request() {
		List<Site> theSites = testSiteDao.getAvailableSitesForReservationRequest(6, "2020-03-13", "2020-03-18", 3, 3);
		assertNotNull(theSites);
		assertEquals(1, theSites.size());
	}
	
	@Test
	public void test_Get_SiteId_From_Site_Number() {
		int siteId = testSiteDao.getSiteIdFromSiteNumber(1, 6);
		assertEquals(617, siteId);
	}

	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	
	
}
