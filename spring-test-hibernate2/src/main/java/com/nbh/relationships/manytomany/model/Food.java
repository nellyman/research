package com.nbh.relationships.manytomany.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @OneToMany(mappedBy = "food")
    private List<FoodType> types = new ArrayList<>();

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


@Embeddable
class FoodTypeId implements Serializable{

    private Long foodId;
    private Long typeId;

    public FoodTypeId() {
    }

    public FoodTypeId(Food food, Type type) {
        this.foodId = food.getId();
        this.typeId = type.getId();
    }

    /**
     * @return foodId
     **/
    public Long getFoodId() {
        return foodId;
    }

    /**
     * @return typeId
     **/
    public Long getTypeId() {
        return typeId;
    }
}



@Entity
@Table(name="FoodMappingToType")
class FoodType{

    @EmbeddedId
    FoodTypeId foodTypeId;

    @ManyToOne
    @MapsId("foodId")
    private Food food;

    @ManyToOne
    @MapsId("typeId")
    private Type type;

    public FoodType() {
    }

    public FoodType(Food food, Type type){
        this.food=food;
        this.type = type;
        this.foodTypeId = new FoodTypeId(food, type);
    }
}


@Entity
class Type{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @OneToMany(mappedBy = "type")
    private List<FoodType> foods = new ArrayList<>();

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
