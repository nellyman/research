package com.nbh.hibernatesearch;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Facet;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

@Entity
@Indexed
public class Manufacturer {

    @Id
    @DocumentId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field
    private String name;

    @ManyToOne
    @Field
    @Facet
    private Country country;

    public Manufacturer() {
    }

    public Manufacturer(String name, Country country) {
        this.name = name;
        this.country = country;
    }


    /**
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * @param name the name being set
     * @return reference to this
     **/
    public Manufacturer setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return country
     **/
    public Country getCountry() {
        return country;
    }

    /**
     * @param country the country being set
     * @return reference to this
     **/
    public Manufacturer setCountry(Country country) {
        this.country = country;
        return this;
    }
}


