package com.nbh.springtesthibernate1;

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

    Engine addPart(Part part){
        parts.add(part);
        return this;
    }

    Engine removePart(Part part){
        parts.remove(part);
        return this;
    }

    /**
     * @return parts
     **/
    public List<Part> getParts() {
        return parts;
    }
}


@Entity
class Part{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    public Part() {
    }

    public Part(String name) {
        this.name = name;
    }
}