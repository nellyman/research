package com.nbh.relationships.onetomany.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NaturalId
    @Column(name= "name", unique = true)
    String name;

    public Building() {
    }

    public Building(String name) {
        this.name = name;
    }

    /**
     * @return id
     **/
    public Long getId() {
        return id;
    }

    /**
     * @return name
     **/
    public String getName() {
        return name;
    }
}




