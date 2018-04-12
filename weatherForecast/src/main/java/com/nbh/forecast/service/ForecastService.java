package com.nbh.forecast.service;

import com.nbh.forecast.Forecast;
import com.nbh.forecast.location.Location;
import com.nbh.forecast.weather.TemperatureUnit;

public interface ForecastService {
    /**
     * Gets the forecast at the location
     * @param location The location to use.
     * @return A Forecast for the location, using local temperature units
     */
    Forecast getForecast(Location location);

}
