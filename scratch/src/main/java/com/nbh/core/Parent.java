/*
 * Parent.java
 *
 * Created on 22 April 2003, 17:08
 */

package com.nbh.core;

/**
 *
 * @author  neal and rachel
 */
public class Parent {
    
    public String name = "parent";
    public String name2 = "pHello";
    public String name3 = "parentHello";
    
    /** Creates a new instance of Parent */
    public Parent() {
        System.out.println("Parent no args constructor");
    }
    
    public Parent(String name){
     
        System.out.println("Parent constructor called with "+name);
    }
    
    public String getName1(){
         
     String localVar = "bob";   
     return(name+"  "+localVar);   
    }
    
    public String getName2(){
        
        return (name2);
    }
    
    public String getName3(){
        
        return (name3);
    }
    public String parentMethodOnly(){
    
        return("This method is in the parent object "+parentName);
    }
    private String parentName="bubbles";
    
    
}
