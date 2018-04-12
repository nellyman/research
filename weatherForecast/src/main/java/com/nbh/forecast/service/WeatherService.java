package com.nbh.forecast.service;

import com.nbh.forecast.location.Location;
import com.nbh.forecast.weather.Weather;

public interface WeatherService {

    /**
     * Gets the weather at the location
     * @param location The Location
     * @return Weather at the location
     */
    Weather getWeather(Location location);
}
