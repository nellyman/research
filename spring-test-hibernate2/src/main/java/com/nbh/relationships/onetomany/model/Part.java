package com.nbh.relationships.onetomany.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Part{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    public Part() {
    }

    public Part(String name) {
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
