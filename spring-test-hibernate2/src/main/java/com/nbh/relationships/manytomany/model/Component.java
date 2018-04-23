package com.nbh.relationships.manytomany.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A unidirectional Mapping component ->supplier
 */
@Entity
public class Component {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    public Component() {
    }

    public Component(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "Component_Supplier",
            joinColumns = @JoinColumn(name = "supplierId"),
            inverseJoinColumns = @JoinColumn(name = "componentId"))
    List<Supplier> suppliers = new ArrayList<>();

    /**
     * @return suppliers
     **/
    public List<Supplier> getSuppliers() {
        return suppliers;
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





