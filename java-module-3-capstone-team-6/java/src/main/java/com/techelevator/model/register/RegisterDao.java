package com.techelevator.model.register;

import java.time.LocalDateTime;

public interface RegisterDao {
	
	public void saveUser(String username, String password, String email, String passwordHint);
	
	public Register getValidUserWithPassword(String username, String password);

	public void changePassword(Register userFromSession, String newPassword);
	
	public LocalDateTime retrieveRegistrationDate(Register theUser);
	
	public String getPasswordHintForUsername(String username);

}
