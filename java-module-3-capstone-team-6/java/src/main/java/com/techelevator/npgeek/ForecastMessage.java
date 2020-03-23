package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.weather.Weather;

public class ForecastMessage {
	
	private List<Weather> fiveDayForecast;
	
	public ForecastMessage(List<Weather> fiveDayForecast) {
		this.fiveDayForecast = fiveDayForecast;
	}

	public List<String> getDisplayRecommendation() {

		List<String> dailyRecommendations = new ArrayList<String>();
		
		for (Weather aDay : fiveDayForecast) {
			String recommendation = "";
			
			switch (aDay.getForecast()) {

			case "snow":
				recommendation += "Looks like snow! Pack snowshoes!";
				break;

			case "rain":
				recommendation += "Looks like rain! Pack raingear and wear waterproof shoes.";
				break;

			case "thunderstorms":
				recommendation += "Beware of thunderstorms! Seek shelter and avoid hiking on exposed ridges.";
				break;

			case "sunny":
				recommendation += "Plenty of sunshine means pack plenty of sunblock!";
				break;
				
			default:
				recommendation += "Enjoy your day at the Park!"; 

			}

			if (aDay.getHighTemp() > 75) {
				recommendation += " Stay hydrated. Bring an extra gallon of water.";
			}
			if (aDay.getLowTemp() < 20) {
				recommendation += " Exposure to frigid temperatures can result in serious and life threatening harm. Dress warmly!";
			}
			if (aDay.getHighTemp() - aDay.getLowTemp() > 20) {
				recommendation += " Wide range of temperatures expected. Wear breathable layers.";
			}

			dailyRecommendations.add(recommendation);
		}

		return dailyRecommendations;
	}
	
}
