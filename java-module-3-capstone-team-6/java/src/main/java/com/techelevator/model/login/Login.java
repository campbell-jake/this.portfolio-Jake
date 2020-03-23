package com.techelevator.model.login;

import java.time.LocalDateTime;

public class Login {
	
	private int userId;
	private LocalDateTime loginDate;

	public int getUserId() {
		return userId;
	}
	
	public LocalDateTime getLoginDate() {
		return loginDate;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setLoginDate(LocalDateTime loginDate) {
		this.loginDate = loginDate;
	}
	
}
