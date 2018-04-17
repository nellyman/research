package com.nbh.relationships.onetomany.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Janitor extends Person{

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
