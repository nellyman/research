package com.nbh.forecast.service;

import com.nbh.forecast.location.Location;
import com.nbh.forecast.weather.TemperatureUnit;

public interface LocationService {

    /**
     * Service determined location
     * @return The location
     */
    Location getLocation();

    /**
     * @param name The Name of the location
     * @param units The units used at the location
     * @return A Location with the specified name
     */
    Location getLocationWithName(String name, TemperatureUnit units);
}
