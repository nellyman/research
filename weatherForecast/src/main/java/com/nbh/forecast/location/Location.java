package com.nbh.forecast.location;

import com.nbh.forecast.weather.TemperatureUnit;

import java.util.Objects;

/**
 * Simple implementation of a Location
 */
public class Location {

    private String name;
    private TemperatureUnit temperatureUnits;

    public Location(String name, TemperatureUnit units) {
        this.name = name;
        this.temperatureUnits=units;
    }

    public String getName() {
        return name;
    }

    public TemperatureUnit getTemperatureUnits() {
        return temperatureUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                '}';
    }
}
