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
import image.alien.*;

public class GameApplet extends java.applet.Applet implements Runnable {
    
    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    // declare two instance variables at the head of the program
    private Image dbImage;
    private Graphics dbg;
    
    private AlienControl control;
    
    public void init() {
        
        control= new AlienControl(3,3,350,200);
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
                Thread.sleep(100);
            }
            catch (InterruptedException ex) {
                // do nothing
            }
            
            // set ThreadPriority to maximum value
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }
    
    public void paint(Graphics g){
        
        control.DrawAliens(g);
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
