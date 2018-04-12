/*
 * StaticObject.java
 *
 * Created on 23 June 2003, 11:59
 */

package com.nbh.core;

/**
 *
 * @author  neal and rachel
 */
public class StaticObject {
    
    private static String name;
    
    /** Creates a new instance of StaticObject */
    private StaticObject() {
    }
    
    public static String getName(){
        return name;
    }
    public static void setName(String _name){
     name = _name;   
    }
    
}
