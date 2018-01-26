package com.nbh.springtesthibernate1;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}

@Repository
interface PersonRepo extends CrudRepository<Person, Long>{
    Person findByName(@Param("name") String name);
}


@Entity
class Pupil extends Person{

    @Transient
    int age=7;

    public Pupil() {
    }

    public Pupil(String name){
        super.name=name;
    }

    public String whoAmI(){
        return "I am a Pupil!";
    }
}

@Repository
interface PupilRepo extends CrudRepository<Pupil, Long> {

    Pupil findByName(@Param("name") String name);
}

@Entity
class Teacher extends Person{

    @Transient
    int age=40;

    public Teacher(String name){
        super.name=name;
    }
    public String whoAmI(){
        return "I am a Teacher!";
    }
}

@Repository
interface TeacherRepo extends CrudRepository<Teacher, Long> {

    Teacher findByName(@Param("name") String name);
}


@Entity
class Janitor extends Person{

    @ManyToOne
    School school;

    public Janitor() {
    }

    public Janitor(String name){
        super.name=name;
    }
    public String whoAmI(){
        return "I am a Janitor!";
    }

    /**
     * @param school the school being set
     * @return reference to this
     **/
    public Janitor setSchool(School school) {
        this.school = school;
        return this;
    }

    /**
     * @return school
     **/
    public School getSchool() {
        return school;
    }
}

@Repository
interface JanitorRepo extends CrudRepository<Janitor, Long> {

    Janitor findByName(@Param("name") String name);
}