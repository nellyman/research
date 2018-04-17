package com.nbh.relationships.onetomany.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * Single Table inheritance example.
 * Uses a Single Table, 'Person' to store the Pupils and Teachers.
 * A 'DType' column is automatically created, that stores what
 * type the entry is-
 *
 * Hibernate: insert into person (id, name, dtype) values (null, ?, 'Pupil')
 * binding parameter [1] as [VARCHAR] - [Tom]
 * Hibernate: insert into person (id, name, dtype) values (null, ?, 'Teacher')
 * binding parameter [1] as [VARCHAR] - [MrsSmith]
 *
 * Note this is the default inheritance JPA Strategy, so no further annotations
 * are required.
 *
 */

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NaturalId
    @Column(name= "name", unique = true)
    String name;

    @Transient
    int age=20;

    public Person(){
    }

    public Person(String name){
        this.name=name;
    }
    public String whoAmI(){
        return "I am a Person!";
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
     * @return age
     **/
    public int getAge() {
        return age;
    }
}


