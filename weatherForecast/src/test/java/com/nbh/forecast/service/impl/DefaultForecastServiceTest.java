package com.nbh.forecast.service.impl;

import com.nbh.forecast.Forecast;
import com.nbh.forecast.location.Location;
import com.nbh.forecast.location.LocationFactory;
import com.nbh.forecast.service.ForecastService;
import com.nbh.forecast.service.LocationService;
import com.nbh.forecast.service.TemperatureService;
import com.nbh.forecast.service.WeatherService;
import com.nbh.forecast.weather.TemperatureUnit;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Timer;

import static org.junit.Assert.*;

public class DefaultForecastServiceTest {

    private LocationFactory factory = new LocationFactory();
    private LocationService locationService = new DefaultLocationService();
    private WeatherService weatherService= new DefaultWeatherService();
    private TemperatureService temperatureService= new SlowTemperatureService();
    private ForecastService service = new DefaultForecastService(
            weatherService, temperatureService);

    @Test
    public void shouldGetForecast(){

        Instant start = Instant.now();
        Forecast londonForecast = service.getForecast(
                factory.getLocation("London", TemperatureUnit.CENTIGRADE));
        Instant end = Instant.now();
        System.out.println("londonForecast = " + londonForecast);
        System.out.println("Time = " + Duration.between(start, end));
    }
}