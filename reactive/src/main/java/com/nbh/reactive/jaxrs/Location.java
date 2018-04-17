package com.nbh.reactive.jaxrs;

public class Location {

    private final String name;

    public Location(String name) {
        this.name = name;
    }

    /**
     * @return name
     **/
    public String getName() {
        return name;
    }
}
