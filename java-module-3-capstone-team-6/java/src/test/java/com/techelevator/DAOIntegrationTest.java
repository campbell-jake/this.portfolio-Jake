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

import com.techelevator.model.login.JdbcLoginDao;
import com.techelevator.model.park.JdbcParkDao;
import com.techelevator.model.park.Park;
import com.techelevator.model.register.JdbcRegisterDao;
import com.techelevator.model.survey.JdbcSurveyDao;
import com.techelevator.model.weather.JdbcWeatherDao;

public class DAOIntegrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
		
	private JdbcLoginDao testLoginDao;
	private JdbcParkDao testParkDao;
	private JdbcRegisterDao testRegisterDao;
	private JdbcSurveyDao testSurveyDao;
	private JdbcWeatherDao testWeatherDao;

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
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
		testLoginDao = new JdbcLoginDao(dataSource);
		testParkDao = new JdbcParkDao(dataSource);
		testRegisterDao = new JdbcRegisterDao(dataSource);
		testSurveyDao = new JdbcSurveyDao(dataSource);
		testWeatherDao = new JdbcWeatherDao(dataSource);
				
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	public DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void testGetAllParks() {
		List<Park> allParks = testParkDao.getAllParks();
		assertNotNull(allParks);
		assertEquals(10, allParks.size());	
	}
	
}
