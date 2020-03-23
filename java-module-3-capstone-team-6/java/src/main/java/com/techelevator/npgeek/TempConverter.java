package com.techelevator.npgeek;

public class TempConverter {
	
	private double fahrenheitTemperature;
	
	public TempConverter(double fahrenheit) {
		this.fahrenheitTemperature = fahrenheit;
	}

	public double getCelsiusTemperature() {
		double celsius = (fahrenheitTemperature - 32) * 5/9;
		return celsius;
	}
	
	public double getFahrenheitTemperature() {
		return fahrenheitTemperature;
	}
}
