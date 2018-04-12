package com.nbh.reactive;

import com.nbh.forecast.Forecast;
import com.nbh.forecast.location.LocationFactory;
import com.nbh.forecast.service.ForecastService;
import com.nbh.forecast.service.LocationService;
import com.nbh.forecast.service.TemperatureService;
import com.nbh.forecast.service.WeatherService;
import com.nbh.forecast.service.impl.DefaultForecastService;
import com.nbh.forecast.service.impl.DefaultLocationService;
import com.nbh.forecast.service.impl.DefaultWeatherService;
import com.nbh.forecast.service.impl.SlowTemperatureService;
import com.nbh.forecast.weather.TemperatureUnit;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class TestWeatherServiceNonReactive {

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
        System.out.println("londonForecast = " + londonForecast);
        Forecast tokyoForecast = service.getForecast(
                factory.getLocation("Tokyo", TemperatureUnit.CENTIGRADE));
        Forecast laForecast = service.getForecast(
                factory.getLocation("LA", TemperatureUnit.FAHRENHEIT));
        Instant end = Instant.now();

        System.out.println("Time = " + Duration.between(start, end));
    }
}
