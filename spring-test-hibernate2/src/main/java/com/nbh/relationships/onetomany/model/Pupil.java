package com.nbh.relationships.onetomany.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Pupil extends Person{

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
