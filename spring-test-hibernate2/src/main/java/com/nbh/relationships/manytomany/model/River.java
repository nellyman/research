package com.nbh.relationships.manytomany.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class River{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @ManyToMany(mappedBy = "rivers")
    private List<Country> countries = new ArrayList<>();

    public River() {
    }

    public River(String name) {
        this.name = name;
    }

    /**
     * @return countries
     **/
    public List<Country> getCountries() {
        return countries;
    }
}

