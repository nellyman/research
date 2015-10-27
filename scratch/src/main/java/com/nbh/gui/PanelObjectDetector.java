/*
 * PanelObjectDetector.java
 *
 * Created on 28 April 2003, 20:07
 */

package com.nbh.gui;

/**
 *
 * @author  neal and rachel
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelObjectDetector extends JPanel{

    JPanel panel;
    /** Creates a new instance of PanelObjectDetector */
    public PanelObjectDetector() {
        
        panel = new JPanel(true);
        this.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                System.out.println("x = "+evt.getX()+"  y =  "+evt.getY());
                Component cmp = evt.getComponent();                
                System.out.println("Using evt.getComponent : "+cmp.getName());
                cmp = getComponentAt(evt.getX(), evt.getY());
                System.out.println("Using componentAt : "+cmp.getName());
            }
        });
        panel.add(new JButton("hello"));
        panel.add(new JTextArea("hi"));
        Balloon b= new Balloon(10,10);
        this.add(b);
        //b.update(this.getGraphics());
        this.add(panel);
        
    }
    
    public static void main(String[] args){
        PanelObjectDetector pod=new PanelObjectDetector();
        DefaultFrame frame = new DefaultFrame(pod, "Panel Object Detector",false, 115,300);        
        
    }
    
}
