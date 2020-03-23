package com.techelevator.authentication;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.techelevator.model.register.Register;
import com.techelevator.model.register.RegisterDao;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionAuthentication implements Authentication{
	
	public static final String USER_KEY = "appCurrentUser";
	
	private HttpSession session;
	private RegisterDao registerDao;
	
	@Autowired
	public SessionAuthentication(HttpSession session, RegisterDao registerDao) {
		this.session = session;
		this.registerDao = registerDao;
	}
	
	@Override
	public Register getCurrentUser() {
		return (Register) session.getAttribute(USER_KEY);
	}

	@Override
	public boolean signIn(String username, String password) {
		
		Register authenticatedUser = registerDao.getValidUserWithPassword(username, password);
		if(authenticatedUser != null) {
			session.setAttribute(USER_KEY, authenticatedUser);
			return true;
		}
		else {
			return false;
		}	
	}

	@Override
	public void logOff() {
		session.removeAttribute(USER_KEY);
		session.invalidate();
		
	}

	@Override
	public boolean changePassword(String existingPassword, String newPassword) {
		
		 Register userFromSession = (Register) session.getAttribute(USER_KEY);
	        if (userFromSession == null) {
	            return false;
	        }
	        Register userFromDb = registerDao.getValidUserWithPassword(userFromSession.getUsername(), existingPassword);
	        if (userFromDb != null && userFromDb.getUserId() == userFromSession.getUserId()) {
	            registerDao.changePassword(userFromSession, newPassword);
	            return true;
	        } else {
	            return false;
	        }
	}

	@Override
	public boolean isLoggedin() {
		return session.getAttribute(USER_KEY) != null;
	}

}
