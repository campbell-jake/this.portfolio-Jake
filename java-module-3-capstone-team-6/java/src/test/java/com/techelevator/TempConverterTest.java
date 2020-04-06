package com.techelevator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.techelevator.npgeek.TempConverter;

public class TempConverterTest {

	@Test
	public void testGetFahrenheitTemperature() {
		
		double testFahrenheit = 212;
		TempConverter testObject = new TempConverter(testFahrenheit);
		
		double expectedFahrenheit = 212;
		double fahrenheitTemperature = testObject.getFahrenheitTemperature();
		
		assertEquals("The temperature values do not match. ", expectedFahrenheit, fahrenheitTemperature, 0.00);
	}

	@Test
	public void testGetCelsiusTemperature() {
		
		double testFahrenheit = 212;
		TempConverter testObject = new TempConverter(testFahrenheit);
		
		double expectedCelsius = 100;
		double celsiusTemperature = testObject.getCelsiusTemperature();
		
		assertEquals("The temperature values do not match. ", expectedCelsius, celsiusTemperature, 0.00);
	}
}
