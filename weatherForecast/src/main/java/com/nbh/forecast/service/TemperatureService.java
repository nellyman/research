package com.nbh.forecast.service;

import com.nbh.forecast.location.Location;
import com.nbh.forecast.weather.Temperature;
import com.nbh.forecast.weather.TemperatureUnit;

public interface TemperatureService {

    /**
     *
     * @param location The Location to get the Temperature from
     * @return A Temperature measurement
     */
    Temperature getTemperature(Location location);
}
