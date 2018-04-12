package com.nbh.forecast.service.impl;

import com.nbh.forecast.Forecast;
import com.nbh.forecast.location.Location;
import com.nbh.forecast.service.ForecastService;
import com.nbh.forecast.service.TemperatureService;
import com.nbh.forecast.service.WeatherService;

import java.time.LocalDateTime;

public class DefaultForecastService implements ForecastService {

    private WeatherService weatherService;
    private TemperatureService temperatureService;


    public DefaultForecastService() {
    }

    public DefaultForecastService(WeatherService weatherService,
                                  TemperatureService temperatureService) {
        this.weatherService = weatherService;
        this.temperatureService = temperatureService;
    }

    @Override
    public Forecast getForecast(Location location) {
        return new Forecast(
                LocalDateTime.now(),
                location,
                temperatureService.getTemperature(location),
                weatherService.getWeather(location));
    }
}
