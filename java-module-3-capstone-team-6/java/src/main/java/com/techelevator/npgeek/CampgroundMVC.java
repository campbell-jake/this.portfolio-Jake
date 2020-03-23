package com.techelevator.npgeek;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.authentication.Authentication;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.login.*;
import com.techelevator.model.park.*;
import com.techelevator.model.register.*;
import com.techelevator.model.survey.*;
import com.techelevator.model.weather.*;

@Controller
public class CampgroundMVC {
	
	@Autowired
	ParkDao parkDao;
	
	@Autowired
	WeatherDao weatherDao;
	
	@Autowired
	SurveyDao surveyDao;
	
	@Autowired
	RegisterDao registerDao;
	
	@Autowired
	LoginDao loginDao;
	
	@Autowired
	Authentication authenticate;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String displayLandingPage() {
		
		return "landingPage";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.GET)
	public String displayRegistrationPage(ModelMap modelHolder) {
		if (!modelHolder.containsAttribute("register")) {
            modelHolder.put("register", new Register());
        }
		return "register";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("register") Register aUser
							 , BindingResult theErrors
							 , RedirectAttributes flashMap) {
		
		if(theErrors.hasErrors()) {
			flashMap.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "register", theErrors);
			flashMap.addFlashAttribute("register", aUser);
			flashMap.addFlashAttribute("registerMessage", "*Please ensure all fields are filled out correctly");
			return "redirect:/register";
		}
		
		registerDao.saveUser(aUser.getUsername(), aUser.getPassword(), aUser.getEmail(), aUser.getPasswordHint());
		
		return "redirect:/login";
		
	}
	
	@RequestMapping (path = "/login", method = RequestMethod.GET)
	public String displayLoginPage(ModelMap modelHolder) {
		if (!modelHolder.containsAttribute("login")) {
            modelHolder.put("login", new Register());
        }
		
		return "login";
	}
	
	@RequestMapping (path = "/forgotPassword", method = RequestMethod.GET)
	public String displayForgotPasswordPage() {
		
		return "/forgotPassword";
	}
	
	@RequestMapping(path = "/forgotPassword", method = RequestMethod.POST)
	public String givePasswordHintOnLoginPage(RedirectAttributes flashMap
											, @RequestParam String username) {
		
		String passwordHint = registerDao.getPasswordHintForUsername(username);
		
		if(passwordHint.isEmpty()) {
			flashMap.addFlashAttribute("passwordHint", "Invalid Username");
		}
		else {
			flashMap.addFlashAttribute("passwordHint", "Password Hint: " + passwordHint);
		}
		
		return "redirect:/login";
		
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String loginUser(@RequestParam String username
						  , @RequestParam String password
						  , RedirectAttributes flashMap) {
		
		if(authenticate.signIn(username, password)) {
			Register currentUser = authenticate.getCurrentUser();
			loginDao.recordLoginDate(currentUser);
			return "redirect:/homepage";
		}
		else {
			flashMap.addFlashAttribute("loginMessage", "Invalid Username or Password.");
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path = "/logoff", method = RequestMethod.GET)
	public String logoffUser() {
		
		authenticate.logOff();
		
		return "redirect:/";
		
	}
	
	@RequestMapping(path = "/userAccount", method = RequestMethod.GET)
	public String displayAccountInfoPage(HttpSession theSession) throws UnauthorizedException{
		
		if(authenticate.isLoggedin()) {
		
		Register currentUser = authenticate.getCurrentUser();
		Login lastLogin = loginDao.retrieveLastLoginDate(currentUser);
	
		theSession.setAttribute("lastLogin", lastLogin);
		
		LocalDateTime registrationTime = registerDao.retrieveRegistrationDate(currentUser);
		
		theSession.setAttribute("registrationDate", registrationTime);
		
		return "userAccount";
		
		}
		else {
			throw new UnauthorizedException();
		}
	}
	
	@RequestMapping(path= "/changePassword", method = RequestMethod.GET)
	public String displayChangePasswordPage(ModelMap modelHolder) throws UnauthorizedException{
	
		if(authenticate.isLoggedin()) {
		if (!modelHolder.containsAttribute("register")) {
            modelHolder.put("register", new Register());
        }

		return "changePassword";
		
		}
		else {
			throw new UnauthorizedException();
		}
	}
	
	@RequestMapping(path= "/changePassword", method = RequestMethod.POST)
	public String changePassword(@Valid @ModelAttribute("register") Register aUser
							   , BindingResult theResult
							   , RedirectAttributes flashMap
							   , @RequestParam String currentPassword) {
		
//		if(theResult.hasErrors()) {
//			flashMap.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "register", theResult);
//			flashMap.addFlashAttribute("register", aUser);
//			flashMap.addFlashAttribute("unsuccessfulUpdate", "Ensure correct current password and matching updated password fields.");
//			return "redirect:/changePassword";
//		}
//		
			if(authenticate.changePassword(currentPassword, aUser.getPassword())) { 
			flashMap.addFlashAttribute("successfulUpdate", "Password successfully changed!");
			return "redirect:/userAccount";
			}
		
		else {
			flashMap.addFlashAttribute("unsuccessfulUpdate", "Ensure correct current password and matching updated password fields.");
			return "redirect:/changePassword";
		}
		
	}
	
	@RequestMapping(path = "/homepage", method = RequestMethod.GET)
	public String displayHomePage(HttpSession sessionMap) throws UnauthorizedException{
		
		if(authenticate.isLoggedin()) {
		List<Park> allParks = parkDao.getAllParks();
		
		sessionMap.setAttribute("parks", allParks);
		
		return "homepage";
		
		}
		else {
			throw new UnauthorizedException();
		}
	}
	
	@RequestMapping(path = "/parkDetail", method = RequestMethod.GET)
	public String displayParkDetailPage(HttpSession tempSession, @RequestParam String parkCode) throws UnauthorizedException{
		
		if(authenticate.isLoggedin()) {
		List<Weather> fiveDayForecast = weatherDao.getWeatherByParkCode(parkCode);
		tempSession.setAttribute("forecast", fiveDayForecast);
		
		List<Weather> fiveDayForecastCelsius = weatherDao.getWeatherByParkCodeInCelsius(parkCode);
		tempSession.setAttribute("forecastCelsius", fiveDayForecastCelsius);
		
		Park aPark = parkDao.getParkByParkCode(parkCode);
		
		tempSession.setAttribute("park", aPark);
		
		ForecastMessage recommendations = new ForecastMessage(fiveDayForecast);
		
		tempSession.setAttribute("recommendations", recommendations);
		
		return "parkDetail";
		
		}
		else {
			throw new UnauthorizedException();
		}
	}
	
	@RequestMapping(path= "/getTempPreference", method = RequestMethod.POST)
	public String displayParkDetailWithTempPreference(HttpSession tempSession, @RequestParam String tempPreference) {
		
		tempSession.setAttribute("tempPreference", tempPreference);
		
		return "parkDetail";
	}
	
	@RequestMapping(path = "/survey", method = RequestMethod.GET)
	public String displaySurveyPage(ModelMap modelHolder) throws UnauthorizedException{
		
		if(authenticate.isLoggedin()) {
		if (!modelHolder.containsAttribute("survey")) {
            modelHolder.put("survey", new Survey());
        }
		
        return "survey";
        
		}
		else {
			throw new UnauthorizedException();
		}
	}
	
	@RequestMapping(path = "/survey", method = RequestMethod.POST)
	public String saveSurvey(@Valid @ModelAttribute("survey") Survey theSurvey
								  , BindingResult theResult
								  , RedirectAttributes flashMap) {
		
		if(theResult.hasErrors()) {
			flashMap.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", theResult);
			flashMap.addFlashAttribute("survey", theSurvey);
			
			return "redirect:/survey";
			
		}
		
		surveyDao.saveSurveyInput(theSurvey.getParkCode(), theSurvey.getEmailAddress(), theSurvey.getState(), theSurvey.getActivityLevel());
		
		return "redirect:/favoriteParks";
	}
	
	@RequestMapping(path = "/favoriteParks", method = RequestMethod.GET)
	public String displayFavoriteParksPage(HttpServletRequest theRequest) throws UnauthorizedException{
		
		if(authenticate.isLoggedin()) {
			
		List<String[]> surveyResults = surveyDao.getAllSurveys();
		
		theRequest.setAttribute("favoriteParks", surveyResults);
		
		return "favoriteParks";
		
		}
		else {
			throw new UnauthorizedException();
		}
	}

}
