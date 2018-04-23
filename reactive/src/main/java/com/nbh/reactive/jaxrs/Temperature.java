package com.nbh.reactive.jaxrs;

public class Temperature {

    private final Double temperature;
    private final String scale;

    public Temperature(Double temperature, String scale) {
        this.temperature = temperature;
        this.scale = scale;
    }

    /**
     * @return temperature
     **/
    public Double getTemperature() {
        return temperature;
    }


    /**
     * @return scale
     **/
    public String getScale() {
        return scale;
    }

}
