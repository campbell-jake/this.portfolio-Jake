package com.techelevator.model.register;

import java.time.LocalDateTime;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Register {
	
	private int userId;
	
	@NotBlank(message="*Please enter a Username")
	@Size(min = 4, max = 20, message = "*Your username must be between 4 and 20 characters")
	private String username;
	
	@NotBlank(message = "*Please enter a password")
	@Size(min = 8, max = 20, message = "*Your password must be between 8 and 20 characters")
	private String password;
	
	@NotBlank(message="*Reenter your password")
	private String confirmPassword;  
	
	@SuppressWarnings("unused")
	private boolean passwordMatching;
	@AssertTrue(message="*Passwords must match") 
	public boolean isPasswordMatching() { 
		if(password != null) {
			return password.equals(confirmPassword);
		} else {
			return false;
		}
	}
	
//	@NotBlank(message = "*Please enter a new password")
//	@Size(min = 8, max = 20, message = "*Your new password must be between 8 and 20 characters")
//	private String newPassword;
//	
//	@NotBlank(message="*Reenter your new password")
//	private String confirmNewPassword;  
//	
//	@SuppressWarnings("unused")
//	private boolean newPasswordMatching;
//	@AssertTrue(message="*Updated passwords must match") 
//	public boolean isnewPasswordMatching() { 
//		if(newPassword != null) {
//			return newPassword.equals(confirmNewPassword);
//		} else {
//			return false;
//		}
//	}
	
	@NotBlank(message="*Please enter a password hint")
	@Size(min = 5, max = 30, message = "*Your password hint should be between 5 and 30 characters")
	private String passwordHint;
	
	@NotBlank(message="*Please enter an email address")
	@Email(message="*Please enter a valid email address")
	private String email;
	
	@NotBlank(message="*Reenter your email address")
	private String confirmEmail;  
	
	@SuppressWarnings("unused")
	private boolean emailMatching; 
	@AssertTrue(message="*Emails must match") 
	public boolean isEmailMatching() { 
		if(email != null) {
			return email.equals(confirmEmail);
		} else {
			return false;
		}
	}
	
	private LocalDateTime registrationDate;
	
	// date and time of last login goes here
	
	// Getters
	
	public int getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public String getEmail() {
		return email;
	}
	public String getConfirmEmail() {
		return confirmEmail;
	}
	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}
	public String getPasswordHint() {
		return passwordHint;
	}
//	public String getNewPassword() {
//		return newPassword;
//	}
//
//	public String getConfirmNewPassword() {
//		return confirmNewPassword;
//	}
	
	//Setters
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public void setPasswordMatching(boolean passwordMatching) {
		this.passwordMatching = passwordMatching;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}
	public void setEmailMatching(boolean emailMatching) {
		this.emailMatching = emailMatching;
	}
	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

//	public void setNewPassword(String newPassword) {
//		this.newPassword = newPassword;
//	}
//
//	public void setConfirmNewPassword(String confirmNewPassword) {
//		this.confirmNewPassword = confirmNewPassword;
//	}

}
