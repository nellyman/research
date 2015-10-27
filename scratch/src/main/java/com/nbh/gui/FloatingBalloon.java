/*
 * FloatingBalloon.java
 *
 * Created on 08 May 2003, 13:15
 */

package com.nbh.gui;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author  neal and rachel
 */
public class FloatingBalloon implements Runnable {
    
    Balloon balloon=new Balloon(50,100);
    Balloon rogue = new Balloon(50,150);    
    class myCanvas extends Canvas{
     
        public void paint(Graphics g){
            balloon.paintComponent(g);
            rogue.paintComponent(g);
        }        
    }
    myCanvas c;
    
    /** Creates a new instance of FloatingBalloon */
    public FloatingBalloon() {
        myCanvas c =new myCanvas();
        JFrame frame = new JFrame("a floating balloon");
        
        //balloon = new Balloon(50,100);
        //rogue = new Balloon(50, 150);
        //c.add(balloon);
        //c.add(rogue);
        
        frame.getContentPane().add(c);
        
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(600,220);
        frame.setSize(600,420);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter(){
           public void windowStateChanged(WindowEvent e){
               System.out.println("Window Change !!");
               balloon.flushDoubleBuffer();
           }
        });
        Thread t =new Thread(this);
        t.start();        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FloatingBalloon fb= new FloatingBalloon();
    }
    
    public void run() {
     
        int x=balloon.getX(); 
        int speed =balloon.getSpeed(); 
        Thread t = Thread.currentThread();
        while (true){
            x = x+speed;
            move(t, x);
            if (x>400){
                speed=(-speed);
                balloon.setSpeed(speed);
            }
            if (x<4){
                speed=Math.abs(speed);
                balloon.setSpeed(speed);
            }
        } 
    }
    
    private synchronized void move(Thread t, int x){
        t.setPriority(Thread.MAX_PRIORITY);
        balloon.setX(x); 
        c.repaint(); 
        t.setPriority(Thread.MIN_PRIORITY);
        try{
            t.sleep(20);
        }
        catch(InterruptedException ioe){
            
        }
        
    }
    
}
