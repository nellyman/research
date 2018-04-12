package com.nbh.gui.game.alien;

/*
 * DisplayApplet.java
 *
 * Created on 13 October 2002, 14:38
 */

/**
 *
 * @author  neal and rachel
 */

import java.awt.Graphics;
import java.awt.Image;

public class DisplayApplet extends java.applet.Applet implements Runnable {

    /** Initialization method that will be called after the applet is loaded
     *  into the browser.
     */
    // declare two instance variables at the head of the program
    private Image dbImage;
    private Graphics dbg;

    private final MoveableObject[] MoveableObjectList = new MoveableObject[5];

    @Override
    public void init() {

        try{
            MoveableObjectControl.setMaxXPosn(350);
            MoveableObjectControl.setMaxYPosn(200);
            for (int i=0;i<5;i++){
                // MoveableObject ball=new MoveableObject(50+(i*12),50+(i*12),i+1,1);
                final MoveableObject ball=new MoveableObject(50+(i*12),50+(i*12),0,i+1);
                this.MoveableObjectList[i]= ball;
            }
        }
        catch(final Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start() {
        // define a new thread
        final Thread th = new Thread(this);
        // start this thread
        th.start();
    }

    @Override
    public void run() {
        // lower ThreadPriority
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        // run a long while (true) this means in our case "always"
        while (true) {
            // repaint the applet
            this.repaint();
            try {
                // Stop thread for 20 milliseconds
                Thread.sleep(20);
            }
            catch (final InterruptedException ex) {
                // do nothing
            }

            // set ThreadPriority to maximum value
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }

    @Override
    public void paint(final Graphics g){

        for (int i=0;i<5;i++){
            final MoveableObject ball=this.MoveableObjectList[i];
            ball.DrawObject(g);
        }
        //ball.DrawObject(g);
    }

    /** Update - Method, implements double buffering */
    @Override
    public void update(final Graphics g) {
        // initialize buffer
        if (this.dbImage == null) {
            this.dbImage = this.createImage(this.getSize().width, this.getSize().height);
            this.dbg = this.dbImage.getGraphics();

        }

        // clear screen in background
        this.dbg.setColor(this.getBackground());
        this.dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // draw elements in background
        this.dbg.setColor(this.getForeground());
        this.paint(this.dbg);

        // draw image on the screen
        g.drawImage(this.dbImage, 0, 0, this);

    }

}
