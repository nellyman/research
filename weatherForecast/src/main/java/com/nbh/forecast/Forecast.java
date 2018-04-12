package com.nbh.forecast;

import com.nbh.forecast.location.Location;
import com.nbh.forecast.weather.Temperature;
import com.nbh.forecast.weather.Weather;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Objects;

public class Forecast {

    private LocalDateTime time;
    private Location location;
    private Temperature temperature;
    private Weather weather;

    public Forecast(LocalDateTime time, Location location, Temperature temperature, Weather weather) {
        this.time = time;
        this.location = location;
        this.temperature = temperature;
        this.weather = weather;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Location getLocation() {
        return location;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Weather getWeather() {
        return weather;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "time=" + time +
                ", location=" + location +
                ", temperature=" + temperature +
                ", weather=" + weather +
                '}';
    }

    /**
     * Forecast is deemed identical if the
     * location and time is the same.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return Objects.equals(time, forecast.time) &&
                Objects.equals(location, forecast.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(time, location);
    }
}
