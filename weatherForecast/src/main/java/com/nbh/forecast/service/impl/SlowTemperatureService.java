package com.nbh.forecast.service.impl;

import com.nbh.forecast.location.Location;
import com.nbh.forecast.service.TemperatureService;
import com.nbh.forecast.weather.Temperature;
import com.nbh.forecast.weather.TemperatureUnit;

import java.util.Random;

public class SlowTemperatureService implements TemperatureService {

    @Override
    public Temperature getTemperature(Location location) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ignored) {}

        return new Temperature(
                (double) (new Random().nextInt(20)+30),
                location.getTemperatureUnits()
        );
    }
}
