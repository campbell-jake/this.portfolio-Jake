package com.techelevator.authentication;

import com.techelevator.model.register.Register;

public interface Authentication {

	Register getCurrentUser();
	
	boolean signIn(String username, String password);
	
	void logOff();
	
	boolean changePassword(String existingPassword, String newPassword);
	
	boolean isLoggedin();
}
