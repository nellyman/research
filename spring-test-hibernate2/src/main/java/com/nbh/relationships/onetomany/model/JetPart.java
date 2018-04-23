package com.nbh.relationships.onetomany.model;

import javax.persistence.*;

@Entity
public class JetPart{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    public JetPart(String name) {
        this.name = name;
    }

    public JetPart() {
    }

}
