package com.nbh.reactive.jaxrs;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse {

    private long processingTime;
    private List<Forecast> forecasts = new ArrayList<>();

    /**
     * @param processingTime the processingTime being set
     * @return reference to this
     **/
    public ServiceResponse setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
        return this;
    }

    public ServiceResponse forecasts(List<Forecast> forecasts){
        this.forecasts = forecasts;
        return this;
    }

    /**
     * @return processingTime
     **/
    public long getProcessingTime() {
        return processingTime;
    }

    /**
     * @return forecasts
     **/
    public List<Forecast> getForecasts() {
        return forecasts;
    }
}
