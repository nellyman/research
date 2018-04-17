package com.nbh.relationships.onetomany.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Teacher extends Person{

    @Transient
    int age=40;

    public Teacher(String name){
        super.name=name;
    }
    public String whoAmI(){
        return "I am a Teacher!";
    }
}
