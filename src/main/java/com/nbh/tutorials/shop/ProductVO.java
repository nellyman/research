/*
 * ProductVO.java
 *
 * Created on 10 June 2003, 10:35
 *
 * Value object for the shop exam.
 * Value object pattern is useful as it stop lots of calls to the back end beans,
 * which are usually EJBs. EJB may be remote, hence a call to them is time consuming.
 * A Value Object takes a copy of the data and stores it in local scope...
 */

package com.nbh.tutorials.shop;


import java.io.IOException;
import java.util.Map;
import java.io.Serializable;
/**
 *
 * @author  neal and rachel
 */
public class ProductVO implements Serializable {
    
    private String name;
    private String price;
    private String id;
    
    /** Creates a new instance of ProductVO */
    public ProductVO(String id, String name,String price) {        
        this.price=price;
        this.name=name;
        this.id=id;
    }
    public String getPrice(){
        return price;
    }
    public String getName(){
        return name;
    }
    public String getID(){
        return id;
    }
        
}
