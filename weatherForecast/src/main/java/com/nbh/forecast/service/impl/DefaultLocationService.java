package com.nbh.forecast.service.impl;

import com.nbh.forecast.location.Location;
import com.nbh.forecast.service.LocationService;
import com.nbh.forecast.weather.TemperatureUnit;

public class DefaultLocationService implements LocationService {

    @Override
    public Location getLocation() {
        throw new UnsupportedOperationException("Not implemented!!");
    }

    @Override
    public Location getLocationWithName(String name, TemperatureUnit units) {
        return new Location(name, units);
    }
}
