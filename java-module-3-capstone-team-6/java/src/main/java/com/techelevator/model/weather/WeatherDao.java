package com.techelevator.model.weather;

import java.util.List;

public interface WeatherDao {
	
	public List<Weather> getWeatherByParkCode(String parkCode);

	List<Weather> getWeatherByParkCodeInCelsius(String parkCode);

}
