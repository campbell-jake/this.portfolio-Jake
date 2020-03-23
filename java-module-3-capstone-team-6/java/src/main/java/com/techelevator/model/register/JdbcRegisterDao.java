package com.techelevator.model.register;

import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcRegisterDao implements RegisterDao{

	private JdbcTemplate jdbcTemplate;

	public JdbcRegisterDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveUser(String username, String password, String email, String passwordHint) {
		
		String sqlSaveUser = "insert into users(username, password, email, password_hint) " +
										"values(?, ?, ?, ?)";
		
		jdbcTemplate.update(sqlSaveUser, username, password, email, passwordHint);
		
	}

	@Override
	public Register getValidUserWithPassword(String username, String password) {
		
		String sqlSearchForUser = "select * " +
								  "from users " +
								  "where username = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, username);
		
		if(results.next()) {
			String storedPassword = results.getString("password");
			if(storedPassword.equals(password)) {
				return mapRowToRegister(results);
			}
			else {
				return null;
			}
		}
		else {
			
			return null;
		}
	}
	
	@Override
	public void changePassword(Register userFromSession, String newPassword) {
		
		String sqlChangePassword = "update users " +
								   "set password = ? " +
								   "where userid = ?";
		
		jdbcTemplate.update(sqlChangePassword, newPassword, userFromSession.getUserId());
		
	}
	
	@Override
	public LocalDateTime retrieveRegistrationDate(Register theUser) {
		
		String sqlRetrieveRegistrationDate = "select registration_date " +
											 "from users " +
											 "where userid = ? ";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlRetrieveRegistrationDate, theUser.getUserId());
		
		LocalDateTime registrationDate = null;
		if(results.next()) {
			registrationDate = results.getTimestamp("registration_date").toLocalDateTime();
		}
		
		return registrationDate;
	}
	
	@Override
	public String getPasswordHintForUsername(String username) {
		
		String sqlPasswordHint = "select password_hint " +
								 "from users " +
								 "where username = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlPasswordHint, username);
		
		String passwordHint = "";
		if(result.next()) {
			passwordHint = result.getString("password_hint");
		}
		
		return passwordHint;
	}
	
	private Register mapRowToRegister(SqlRowSet results) {
		
		Register theUser = new Register();
		theUser.setUserId(results.getInt("userid"));
		theUser.setUsername(results.getString("username"));
		theUser.setPassword(results.getString("password"));
		
		return theUser;
	}
}
