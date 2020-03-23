package com.techelevator.model.reservation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JdbcReservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JdbcReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public Reservation createReservation(int siteId, String reservationName, String fromDate, String toDate) {
		
		LocalDate startDate = LocalDate.parse(fromDate);
		LocalDate endDate = LocalDate.parse(toDate);
		LocalDate bookedDate = LocalDate.now();


		String sqlCreateReservation = "insert into reservation(site_id, name, from_date, to_date, create_date) "+
									  "values(?,?,?,?, now()) ";
		
		jdbcTemplate.update(sqlCreateReservation, siteId, reservationName, startDate, endDate);
		
		Reservation newReservation = new Reservation();
		
		newReservation.setReservationId(getNextReservationId());
		newReservation.setSiteId(siteId);
		newReservation.setReservationName(reservationName);
		newReservation.setStartDate(startDate);
		newReservation.setEndDate(endDate);
		newReservation.setBookedDate(bookedDate);

		return newReservation;
	}
	
	private int getNextReservationId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("select nextval('reservation_reservation_id_seq')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the new department");
		} 
	}
	

	
}
