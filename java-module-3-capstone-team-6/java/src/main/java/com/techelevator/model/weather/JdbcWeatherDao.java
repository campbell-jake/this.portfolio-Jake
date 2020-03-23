package com.techelevator.model.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.TempConverter;


@Component
public class JdbcWeatherDao implements WeatherDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getWeatherByParkCode(String parkCode) {

		List<Weather> fiveDayForecast = new ArrayList<Weather>();
		String sqlGetWeatherByParkCode = "select * from weather " + "where parkcode = ? ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetWeatherByParkCode, parkCode);

		while (results.next()) {
			Weather dailyForecast = mapRowToWeather(results);
			fiveDayForecast.add(dailyForecast);
		}
		
		return fiveDayForecast;
	}
	
	@Override
	public List<Weather> getWeatherByParkCodeInCelsius(String parkCode) {
		
		TempConverter celsiusConversion;
		
		List<Weather> fiveDayForecast = new ArrayList<Weather>();
		String sqlGetWeatherByParkCode = "select * from weather " + "where parkcode = ? ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetWeatherByParkCode, parkCode);

		while (results.next()) {
			Weather dailyForecast = mapRowToWeather(results);
			
			double highTemp = dailyForecast.getHighTemp();
			celsiusConversion = new TempConverter(highTemp);
			double celsiusHighTemp = celsiusConversion.getCelsiusTemperature();
			dailyForecast.setHighTemp(celsiusHighTemp);
			
			
			double lowTemp = dailyForecast.getLowTemp();
			celsiusConversion = new TempConverter(lowTemp);
			double celsiusLowTemp = celsiusConversion.getCelsiusTemperature();
			dailyForecast.setLowTemp(celsiusLowTemp);
			
			fiveDayForecast.add(dailyForecast);
		}
		
		return fiveDayForecast;
	}

	private Weather mapRowToWeather(SqlRowSet results) {

		Weather dailyForecast = new Weather();

		dailyForecast.setParkCode(results.getString("parkcode"));
		dailyForecast.setForecastDay(results.getInt("fivedayforecastvalue"));
		dailyForecast.setLowTemp(results.getInt("low"));
		dailyForecast.setHighTemp(results.getInt("high"));
		dailyForecast.setForecast(results.getString("forecast"));

		return dailyForecast;
	}

}
