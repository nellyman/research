/*
 * DisplayApplet.java
 *
 * Created on 13 October 2002, 14:38
 */

/**
 *
 * @author  neal and rachel
 */

import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.applet.Applet.*;

public class DisplayApplet extends java.applet.Applet implements Runnable {
    
    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    // declare two instance variables at the head of the program
    private Image dbImage;
    private Graphics dbg;
    
    private MoveableObject[] MoveableObjectList = new MoveableObject[5];
    
    public void init() {
        
        try{
            MoveableObjectControl.setMaxXPosn(350);
            MoveableObjectControl.setMaxYPosn(200);
            for (int i=0;i<5;i++){
                // MoveableObject ball=new MoveableObject(50+(i*12),50+(i*12),i+1,1);
                MoveableObject ball=new MoveableObject(50+(i*12),50+(i*12),0,i+1);
                MoveableObjectList[i]= ball;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void start() {
        // define a new thread
        Thread th = new Thread(this);
        // start this thread
        th.start();
    }
    
    public void run() {
        // lower ThreadPriority
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        
        // run a long while (true) this means in our case "always"
        while (true) {
            // repaint the applet
            repaint();
            try {
                // Stop thread for 20 milliseconds
                Thread.sleep(20);
            }
            catch (InterruptedException ex) {
                // do nothing
            }
            
            // set ThreadPriority to maximum value
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }
    
    public void paint(Graphics g){
        
        for (int i=0;i<5;i++){
            MoveableObject ball=(MoveableObject)MoveableObjectList[i];
            ball.DrawObject(g);
        }
        //ball.DrawObject(g);
    }
    
    /** Update - Method, implements double buffering */
    public void update(Graphics g) {
        // initialize buffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
            
        }
        
        // clear screen in background
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
        
        // draw elements in background
        dbg.setColor(getForeground());
        paint(dbg);
        
        // draw image on the screen
        g.drawImage(dbImage, 0, 0, this);
        
    }
    
}
