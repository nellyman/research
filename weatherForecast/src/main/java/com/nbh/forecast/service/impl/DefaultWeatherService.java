package com.nbh.forecast.service.impl;

import com.nbh.forecast.location.Location;
import com.nbh.forecast.service.WeatherService;
import com.nbh.forecast.weather.Weather;

public class DefaultWeatherService implements WeatherService {

    @Override
    public Weather getWeather(Location location) {
        return Weather.SUNNY;
    }
}
