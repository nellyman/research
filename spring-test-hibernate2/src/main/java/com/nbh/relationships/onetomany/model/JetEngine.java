package com.nbh.relationships.onetomany.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Shows a one to many with a Join column,
 * This omits the use of the join table-
 */
@Entity
public class JetEngine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
            @JoinColumn(name = "engineId")
    List<JetPart> parts = new ArrayList<>();

    public JetEngine(String name) {
        this.name = name;
    }

    public JetEngine() {
    }

    public JetEngine addPart(JetPart part){
        parts.add(part);
        return this;
    }

    public JetEngine removePart(JetPart part){
        parts.remove(part);
        return this;
    }
}

