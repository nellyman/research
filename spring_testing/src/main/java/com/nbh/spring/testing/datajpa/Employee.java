package com.nbh.spring.testing.datajpa;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 20)
    private String name;

    // standard getters and setters, constructors


    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    /**
     * @return id
     **/
    public Long getId() {
        return id;
    }

    /**
     * @param id the id being set
     * @return reference to this
     **/
    public Employee setId(Long id) {
        this.id = id;
        return this;
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
    public Employee setName(String name) {
        this.name = name;
        return this;
    }
}

