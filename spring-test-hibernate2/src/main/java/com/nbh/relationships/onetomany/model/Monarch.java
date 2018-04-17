package com.nbh.relationships.onetomany.model;

import javax.persistence.*;

@Entity
public class Monarch{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @OneToOne
    Country country;

    public Monarch() {
    }

    public Monarch(String name) {
        this.name = name;
    }


}