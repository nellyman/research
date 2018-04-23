package com.nbh.relationships.onetomany.model;

import javax.persistence.*;

@Entity
public class Country{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    // unidirectional, flag does not know about the country...
    @OneToOne
    Flag flag;

    // bidirectional monarch knows about their country!
    @OneToOne(mappedBy = "country")
    Monarch monarch;

    public void setMonarch(Monarch monarch){
        this.monarch=monarch;
        monarch.country=this;
    }

    public Country(String name) {
        this.name = name;
    }

    public Country() {
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

    /**
     * @return flag
     **/
    public Flag getFlag() {
        return flag;
    }

    /**
     * @return monarch
     **/
    public Monarch getMonarch() {
        return monarch;
    }

    /**
     * @param flag the flag being set
     * @return reference to this
     **/
    public Country setFlag(Flag flag) {
        this.flag = flag;
        return this;
    }
}