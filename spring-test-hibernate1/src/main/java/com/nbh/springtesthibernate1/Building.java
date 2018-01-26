package com.nbh.springtesthibernate1;

import org.hibernate.annotations.NaturalId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NaturalId
    @Column(name= "name", unique = true)
    String name;

    public Building() {
    }

    public Building(String name) {
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
}

@Entity
class School extends Building{

    @OneToMany(mappedBy = "school") //, cascade = CascadeType.ALL)
    List<Janitor> janitors = new ArrayList<>();


    public School() {
    }

    public School(String name) {
        super(name);
    }

    /**
     * @return janitor
     **/
    public List<Janitor> getJanitors() {
        return janitors;
    }

    /**
     * @param janitor the janitor being set
     * @return reference to this
     **/
    public School addJanitor(Janitor janitor) {
        this.janitors.add(janitor);
        janitor.setSchool(this);
        return this;
    }

    public School removeJanitor(Janitor janitor){
        this.janitors.remove(janitor);
        janitor.setSchool(null);
        return this;
    }
}



@Repository
interface SchoolRepo extends CrudRepository<School, Long>{

    School findByName(@Param("name") String name);
}