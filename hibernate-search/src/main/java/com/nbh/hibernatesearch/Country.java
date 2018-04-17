package com.nbh.hibernatesearch;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Indexed
public class Country {

    @Id
    @DocumentId
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field
    private String name;

    public Country() {
    }

    public Country(String name) {
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

    /**
     * @param name the name being set
     * @return reference to this
     **/
    public Country setName(String name) {
        this.name = name;
        return this;
    }
}

@Repository
interface CountryRepo extends CrudRepository<Country, Long>{


}