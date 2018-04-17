package com.nbh.relationships.manytomany.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A BiDirectional  ManyToMany Mapping
 */
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "CountriesToRivers",
            joinColumns = @JoinColumn(name = "countryId"),
            inverseJoinColumns = @JoinColumn(name = "riversId")
    )
    private List<River> rivers= new ArrayList<>();

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    /**
     * @param rivers the rivers being set
     * @return reference to this
     **/
    public Country setRivers(List<River> rivers) {
        this.rivers = rivers;
        return this;
    }

    public void addRiver(River river){
        this.rivers.add(river);
        river.getCountries().add(this);
    }

    public void removeRiver(River river){
        this.rivers.remove(river);
        river.getCountries().remove(this);
    }

    /**
     * @return rivers
     **/
    public List<River> getRivers() {
        return rivers;
    }
}




