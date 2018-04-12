/*
 * Car.java
 *
 * Created on 13 May 2003, 10:55
 */

package com.nbh.core;

/**
 *
 * @author  neal and rachel
 */

import java.awt.Color;
public class Car extends Vehicle{
    
    public int var=10;
    /** Creates a new instance of Car */
    public Car() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Car c=new Car();
        System.out.println(c.getColour());
        Vehicle v = c;
        System.out.println(v.var);
        System.out.println(v.getColour());
        String s= "dsd".intern();
        int i=100;
        byte b=(byte)i;
        System.out.println(b);
        int x=10;
        c.test((byte)x);
        c.test1(x);
        int[] p=new int[9];
        System.out.println(p[5]);
        Vehicle[] vs=new Vehicle[5];
        System.out.println(vs[3].getColour());
        
    }
    
    public int getNumberOfWheels() {
        return 4;
    }
    
    public Color getColour(String st){     // not overridden (!)
        super.getColour();
        return (Color.BLUE);
    }
    
    public Color getColour(){     
        return(Color.black);
    }
    
    public void test(byte g){     
        int c;
    }
    public void test1(long f){
        
    }
    
}
