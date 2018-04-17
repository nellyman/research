package com.nbh.reactive.jaxrs;

public class Forecast {

    private final Location location;
    private final Temperature temperature;

    public Forecast(Location location, Temperature temperature) {
        this.location = location;
        this.temperature = temperature;
    }

    /**
     * @return location
     **/
    public Location getLocation() {
        return location;
    }

    /**
     * @return temperature
     **/
    public Temperature getTemperature() {
        return temperature;
    }
}
