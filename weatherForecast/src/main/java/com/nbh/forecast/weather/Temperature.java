package com.nbh.forecast.weather;

public class Temperature {

    private Double temperature;
    private TemperatureUnit units;

    public Temperature(Double temperature, TemperatureUnit units) {
        this.temperature = temperature;
        this.units = units;
    }

    public Double getTemperature() {
        return temperature;
    }

    public TemperatureUnit getUnits() {
        return units;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "temperature=" + temperature +
                ", units=" + units +
                '}';
    }
}
