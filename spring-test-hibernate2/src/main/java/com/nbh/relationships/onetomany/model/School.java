package com.nbh.relationships.onetomany.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class School extends Building{

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