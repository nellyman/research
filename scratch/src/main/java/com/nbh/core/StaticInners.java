/*
 * StaticInners.java
 *
 * Created on 10 April 2003, 16:47
 */

package com.nbh.core;

/**
 *
 * @author  neal and rachel
 */
public class StaticInners {
    
    private static int x=0;
    private int y=0;
    
    public static class StaticInnerClass{
        
        private static int staticX=0;
        private int innerY=0;
        public void addToCount(){   
            staticX++;
            x++;
            innerY++;
            System.out.println("Outer static variable is "+StaticInners.x);
            System.out.println("Inner static variable is "+staticX);
            System.out.println("Inner instance variable is "+innerY);
        }        
    }
    
    public void incCount(){        
        y++;
        System.out.println("Instance variable is "+y+"\n\n");
    }
    
    /** Creates a new instance of StaticInners */
    public StaticInners() {        
        StaticInnerClass sty = new StaticInnerClass();
        sty.addToCount();
        this.incCount();
    }
    
    public static void main(String[] args){
        
        
        
        StaticInners in1 = new StaticInners();      
        StaticInners in2 = new StaticInners();
        StaticInners in3 = new StaticInners();
        StaticInners in4 = new StaticInners();
        StaticInners in5 = new StaticInners();
        StaticInners in6 = new StaticInners();
        
    }
    
}
