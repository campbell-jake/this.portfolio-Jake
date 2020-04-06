package com.techelevator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.techelevator.model.weather.Weather;
import com.techelevator.npgeek.ForecastMessage;

public class ForecastMessageTest {

	@Test
	public void testGetDisplayRecommendation() {
		
		List<Weather> testWeatherList = new ArrayList<Weather>();
		Weather dayOne = new Weather();
		dayOne.setForecast("snow");
		dayOne.setLowTemp(18);
		dayOne.setHighTemp(22);
		testWeatherList.add(dayOne);
		
		Weather dayTwo = new Weather();
		dayTwo.setForecast("rain");
		dayTwo.setLowTemp(71);
		dayTwo.setHighTemp(77);
		testWeatherList.add(dayTwo);
		
		Weather dayThree = new Weather();
		dayThree.setForecast("thunderstorms");
		dayThree.setLowTemp(50);
		dayThree.setHighTemp(74);
		testWeatherList.add(dayThree);
		
		Weather dayFour = new Weather();
		dayFour.setForecast("sunny");
		dayFour.setLowTemp(71);
		dayFour.setHighTemp(72);
		testWeatherList.add(dayFour);
		
		Weather dayFive = new Weather();
		dayFive.setForecast("party cloudy");
		dayFive.setLowTemp(71);
		dayFive.setHighTemp(72);
		testWeatherList.add(dayFive);
		
		ForecastMessage testObject = new ForecastMessage(testWeatherList);
		
		List<String> expectedStringArray = new ArrayList<String>(5);
		expectedStringArray.add("Looks like snow! Pack snowshoes! Exposure to frigid temperatures can result in serious and life threatening harm. Dress warmly!");
		expectedStringArray.add("Looks like rain! Pack raingear and wear waterproof shoes. Stay hydrated. Bring an extra gallon of water.");
		expectedStringArray.add("Beware of thunderstorms! Seek shelter and avoid hiking on exposed ridges. Wide range of temperatures expected. Wear breathable layers.");
		expectedStringArray.add("Plenty of sunshine means pack plenty of sunblock!");
		expectedStringArray.add("Enjoy your day at the Park!");
		
		List<String> displayRecommendation = testObject.getDisplayRecommendation();
		
		assertEquals("The weather recommendations do not match. ", expectedStringArray, displayRecommendation);
	}

}
