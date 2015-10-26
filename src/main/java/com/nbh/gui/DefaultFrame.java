/*
 * TestLight.java
 *
 * Created on 11 April 2003, 11:14
 */

package com.nbh.gui;

/**
 *
 * @author  neal and rachel
 */

import javax.swing.*;
import java.awt.*;

public class DefaultFrame extends JFrame{
    
    JScrollPane scroll;
    /** Creates a new instance of TestLight */
    public DefaultFrame(JPanel panel, String title) {
        this(panel, title, false,30,190);
    }
    
    public DefaultFrame(JPanel panel, String title, boolean scrollable) {   
        
        this(panel,title,false,30,190);
    }
    
    public DefaultFrame(JPanel panel, String title, boolean scrollable, int sizeX, int sizeY) {   
        JFrame fr = new JFrame(title);
        int base = 100;
        fr.setBounds(base, (base+sizeX), (base+sizeY), (base+sizeX));
        Container c =fr.getContentPane();
        if (scrollable){
            scroll = new JScrollPane(panel);
            c.add(scroll);           
        }
        else
            c.add(panel);
        fr.show();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DefaultFrame pt = new DefaultFrame(new com.nbh.gui.HelloWorldPanel(),"test");
        
    }
}

class HelloWorldPanel extends JPanel{
    

    public void paintComponent(Graphics g){
        g.drawString("hello world", 30,30);
    }
}

