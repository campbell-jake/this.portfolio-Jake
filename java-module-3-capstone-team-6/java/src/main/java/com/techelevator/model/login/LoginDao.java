package com.techelevator.model.login;

import com.techelevator.model.register.Register;

public interface LoginDao {
	
	public void recordLoginDate(Register theUser);
	
	public Login retrieveLastLoginDate(Register theUser);

}
