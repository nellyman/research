package com.nbh.forecast.location;

import com.nbh.forecast.weather.TemperatureUnit;

import java.util.HashMap;
import java.util.Map;

public class LocationFactory {

    Map<String, Location> locations = new HashMap<>();

    public Location getLocation(String name, TemperatureUnit units){
        if (locations.containsKey(name)){
            return locations.get(name);
        }
        Location location = new Location(name, units);
        locations.put(name, location);
        return location;
    }
}
