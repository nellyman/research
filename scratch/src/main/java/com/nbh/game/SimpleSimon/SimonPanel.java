package com.nbh.game.SimpleSimon;


/*
 * SimonPanel.java
 *
 * Created on 30 March 2003, 21:28
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


/**
 *
 * @author  nhardwick
 */
public class SimonPanel extends javax.swing.JPanel {
    
    int numberOfLights=4;
    JPanel thePanel;
    Light[] lights=new Light[numberOfLights];
    //    Coordinator c;
    
    /** Creates a new instance of SimonPanel */
    public SimonPanel() {
        for (int i=0;i<numberOfLights;i++){
            Light l = new Light(i);
            lights[i] = l;
        }
        
        //  c = new Coordinator(this);
        thePanel=new JPanel();
        init();
        
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
                int x=evt.getX();
                int y=evt.getY();
                // c.checkLights(x,y);
                for (int i=0;i<numberOfLights;i++){
                    Light l = lights[i];
                    l.isLightClicked(x,y);
                }
                
                
            }
        });
    }
    public void init(){
        
        this.repaint();
        
    }
    public void paintComponent(Graphics g){
        for (int i=0;i<numberOfLights;i++){
            Light l = lights[i];
            l.update(g);
        }
    }
    
    
    
}
