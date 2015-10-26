package com.nbh.gui;


// java ImageComponent http://java.sun.com/graphics/people.gif

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.net.MalformedURLException;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class ImageComponent extends javax.swing.JComponent {
    Image img;
    
    ImageComponent(String url) {
        try {
            img = Toolkit.getDefaultToolkit().createImage(new URL(url));
            
        } catch (MalformedURLException e) {
            System.err.println("That ain`t no URL valid!");
        }
    }
    
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        System.err.print(".");
        if ((infoflags & ALLBITS) != 0) {
            System.err.println("all bits received");
            return super.imageUpdate(img, infoflags, x, y, width, height);
        }
        else {
            return true;
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, this);
        System.err.println("after drawImage");
    }
    
    public static void main(String [] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new ImageComponent(args[0]));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setVisible(true);
        System.err.println("End of main()");
    }
}
