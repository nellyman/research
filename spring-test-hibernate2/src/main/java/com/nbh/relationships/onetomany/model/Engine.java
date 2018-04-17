package com.nbh.relationships.onetomany.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class showing a unidirectional mapping
 * of Engine to Parts.
 * Engine knows it's parts, but the parts don't know the engine!
 */
@Entity
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @OneToMany
    List<Part> parts = new ArrayList<>();

    public Engine(String name) {
        this.name = name;
    }

    public Engine() {
    }

    public Engine addPart(Part part){
        parts.add(part);
        return this;
    }

    public Engine removePart(Part part){
        parts.remove(part);
        return this;
    }

    /**
     * @return parts
     **/
    public List<Part> getParts() {
        return parts;
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


