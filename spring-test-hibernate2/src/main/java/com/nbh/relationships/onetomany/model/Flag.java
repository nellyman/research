package com.nbh.relationships.onetomany.model;

import javax.persistence.*;

@Entity
public class Flag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    public Flag(String name) {
        this.name = name;
    }

    public Flag() {
    }
}



