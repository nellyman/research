/*
 * vehicle.java
 *
 * Created on 13 May 2003, 10:55
 */

package com.nbh.tutorials;

/**
 *
 * @author  neal and rachel
 */
import java.awt.Color;

public abstract class Vehicle {
    
    public int var=5;
    /** Creates a new instance of vehicle */
    public Vehicle() {
    }
    
    public abstract int getNumberOfWheels();
    
    public Color getColour(){
        System.out.println("Vehicle.getColor called ");
        return Color.red;
    }
}
