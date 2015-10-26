/*
 * FloatingBalloons.java
 *
 * Created on 08 May 2003, 13:23
 */

package com.nbh.gui;

/**
 *
 * @author  neal and rachel
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class FloatingBalloons implements Runnable {
    
    java.util.List balloons;
    JFrame frame;
    Canvas c;
    Random rand = new Random();
    int balloonsOnScreen=0;
    
    
    /** Creates a new instance of FloatingBalloons */
    public FloatingBalloons() {
        c = new Canvas();
        frame = new JFrame("some floating balloons");
        balloons= new ArrayList(10);
       
        
        for (int i=0;i<10;i++){
            Balloon b = new Balloon(5,(5*i));
            balloons.add(i,b);  
            //c.add(b);
            System.out.println("added "+b.getY());
        }

        Container p = frame.getContentPane();        
        p.add(c);
        
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);
        frame.setVisible(true);
        Thread t =new Thread(this);
        //t.start();
    }
    
    public static void main(String[] args) {
        FloatingBalloons fb= new FloatingBalloons();
    }
    
    
    public void run() {
        
        Thread t = Thread.currentThread();
        while (true){
            for (int i=0;i<10;i++){
                Balloon b = (Balloon)balloons.get(i);
                int x=b.getX();
                int speed =b.getSpeed();
                System.out.println("balloon "+i+" at posn "+x+" and "+b.getY());
                x = x+speed;
                b.setX(x);
                if (x>400){
                    speed=(-speed);
                    b.setSpeed(speed);
                }
                if (x<4){
                    speed=1;
                    b.setSpeed(speed);
                }                                
            }
            frame.getContentPane().repaint();
            
            pause(t);
        }
    }
    
    public void paint(Graphics g){
     
        for (int i=0;i<10;i++){
            Balloon b = (Balloon)balloons.get(i);
            c.paint(g);
        }
        
    }
    
    private void pause(Thread t){
        try{
            t.sleep(50);
        }
        catch(InterruptedException ioe){
        }
    }
    
    
}
