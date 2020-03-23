package com.techelevator.model.weather;

public class Weather {
	
	/************************************************************************************************************************************
	 * Instance Variables
	 ***********************************************************************************************************************************/

	private String parkCode;
	private int forecastDay;
	private double lowTemp;
	private double highTemp;
	private String forecast;
	
	/************************************************************************************************************************************
	 * Getters
	 ***********************************************************************************************************************************/

	public String getParkCode() {
		return parkCode;
	}
	public int getForecastDay() {
		return forecastDay;
	}
	public double getLowTemp() {
		return lowTemp;
	}
	public double getHighTemp() {
		return highTemp;
	}
	public String getForecast() {
		return forecast;
	}
	
	/************************************************************************************************************************************
	 * Setters
	 ***********************************************************************************************************************************/

	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public void setForecastDay(int forecastDay) {
		this.forecastDay = forecastDay;
	}
	public void setLowTemp(double lowTemp) {
		this.lowTemp = lowTemp;
	}
	public void setHighTemp(double highTemp) {
		this.highTemp = highTemp;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
}
