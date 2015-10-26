/*
 * Ballon.java
 *
 * Created on 28 April 2003, 20:21
 */

package com.nbh.gui;

/**
 *
 * @author  neal and rachel
 *
 * Class implements double buffering ... (paintComponent)
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;


public class Balloon extends JPanel{
    
    Image balloon;
    int x;
    int y;
    int speed;
    
    /** Creates a new instance of Ballon */
    public Balloon(int _x, int _y) {
        x=_x;
        y=_y;
        speed = 2;
        balloon= Toolkit.getDefaultToolkit().createImage("C:\\dev\\projects\\tutorials\\Swing\\ballon.gif");
    }
    
    Graphics dbuf;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    //    this.setOpaque(true);
        
//        Rectangle clipRect = g.getClipBounds();
//        if (clipRect != null) {
            //If it's more efficient, draw only the area
            //specified by clipRect.
            //Top-leftmost point = (clipRect.x, clipRect.y)
            //Width, height = clipRect.width, clipRect.height
  //      } else {
            //Paint the entire component.
  //      }
        
     /*   Rectangle r = g.getClipBounds();
        if (dbuf==null)
            dbuf =g.create(0, 0, r.width,r.height);
        //dbuf = g.create();
        dbuf.drawImage(balloon,x,y,this);
        r= dbuf.getClipBounds();
        g=dbuf.create(0,0,r.width,r.height);
        //g=dbuf.create();
        //System.out.println("painted "+y);
      */
        g.drawImage(balloon,x,y,this);
    }
    
    public void flushDoubleBuffer(){
        
        dbuf=null;
    }
    
    public static void main(String[] args){
        
        JFrame frame = new JFrame("a floating balloon");
        Balloon b = new Balloon(5,10);
        frame.getContentPane().add(b);
        frame.getContentPane().setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,220);
        frame.setVisible(true);
    }
    
    
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
