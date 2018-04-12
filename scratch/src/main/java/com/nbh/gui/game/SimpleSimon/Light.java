package com.nbh.gui.game.SimpleSimon;

/*
 * Light.java
 *
 * Created on 19 February 2003, 21:31
 */

/**
 *
 * @author  nhardwick
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Light extends java.awt.Component{
    
    public static int SIZE =75;
    public static int YDOWN = 10;
    public static int maxYBounds = (SIZE*2)+YDOWN;
    public static int minYBounds = YDOWN;
    public int maxBounds;
    public int minBounds;
    
    public static final Color lightOn=Color.red;
    public static final Color lightOff=Color.GREEN;
    public int XPOS;
    private int number;
    private Vector listeners;
    private Color lightValue=Light.lightOff;
    
    public void update(Graphics g){
        
        //Thread t = Thread.currentThread();
        Graphics dblbuf = g.create();
        dblbuf.setColor(lightValue);
        dblbuf.fillOval(XPOS, YDOWN, SIZE, SIZE);
        lightValue=Light.lightOff;
        g=dblbuf;
    }
    
    
    /** Creates a new instance of Light */
    private Light() {
    }
    
    public Light(int number){
        this.number=number;
        if (number==0)
            XPOS=1;
        else
            XPOS = SIZE*number;
        maxBounds = (SIZE+XPOS);
        minBounds = SIZE*number;
        listeners=new Vector();
    }
    
    public void trigger(){
        lightValue=Light.lightOn;
    }
    
    public boolean isLightClicked(int x, int y){
        
        boolean results=false;
        // get x bounds...
        if ((x>minBounds) & (x<maxBounds)){
            if (y>minYBounds){
                if (y<maxYBounds){
                    int i=0;
                    while(i<listeners.size()){
                        LightListener l = (LightListener) listeners.elementAt(i);
                        l.lightTriggered(this);
                        results=true;
                    }
                    System.out.println(number+ " HIT !! ");
                }
            }
        }
        return results;
    }
    
    public void addListener(LightListener listener){
        listeners.addElement(listener);
    }
    
    
    /** Getter for property number.
     * @return Value of property number.
     *
     */
    public int getNumber() {
        return number;
    }
    
    /** Setter for property number.
     * @param number New value of property number.
     *
     */
    public void setNumber(int number) {
        this.number = number;
    }
    
}
