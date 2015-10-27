package com.nbh.gui;

import java.awt.*;
import java.awt.Graphics.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;


public class BouncingCircleFrame{
    
    public static void main(String[] args){
        
        final theFrame f=new theFrame();
        f.addWindowListener(new WindowAdapter(){							//anonymous inner class WindowAdapter
            public void windowClosing(WindowEvent e){				// implement the windowClosing method.
                f.dispose();													// Closes all windows.
                System.exit(0);												// Exists the JVM
            }
        });
        f.show();
        f.run();
    }
}


class theFrame extends Frame implements Runnable{
    
    private showBall b;
    private int x=50,y=50,max=300,min=0;
    boolean goingLarge=false;
    
    public theFrame(){
        super("BounceTest");
        setSize(200,200);
        b=new showBall(x,y);
        add(b);
    }
    
    public void run(){
        Thread tempThread=Thread.currentThread();
        
        while(true){
            if (goingLarge){
                for (int i=min;i<max;i++){
                    b.updateY(i);
                    try{
                        tempThread.sleep(180);
                    }
                    catch (InterruptedException e){}
                }
                goingLarge=false;
            }
            
            
            if (!goingLarge){
                for (int i=max;i>min;i--){
                    b.updateY(i);
                    try{
                        tempThread.sleep(180);
                    }
                    catch (InterruptedException e){}
                }
                goingLarge=true;
            }
            
        }
        
    }
}
// ***************************************************************************************************************
class showBall extends Panel{
    private Color currColor=Color.green;		// Current circle colour.
    
    int x=0,y=0, size=40;
    
    public showBall(int newX,int newY){
        this.updateXY(newX,newY);
    }
    
    public void updateXY(int newX,int newY){
        x=newX;
        y=newY;
        repaint();
    }
    public void updateY(int newValue){
        y=newValue;
        repaint();
    }
    
    public void updateX(int newValue){
        x=newValue;
        repaint();
    }
    
    
    public void paint(Graphics g){
        
        g.drawOval(x,y,size,size);
        g.setColor(currColor);
        g.fillOval(x,y,size,size);
        g.setColor(Color.white);
        g.fillOval((x+size/4),(y+size/4),(size/4),(size/4));
    }
    
}

