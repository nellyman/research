package com.nbh.game.SimpleSimon;

/*
 * SSimon.java
 *
 * Created on 30 March 2003, 21:10
 */

/**
 *
 * @author  nhardwick
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimonFrame extends javax.swing.JFrame{
    
     private SimonPanel gamePanel;
    
    /** Creates a new instance of SSimon */
    public SimonFrame() {
        
        setBounds(100, 130, 310, 130);
        initComponents();
        show();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void initComponents(){
        gamePanel = new SimonPanel();
        gamePanel.setSize(100,100);
        Container c= getContentPane();
        c.add(gamePanel);
        this.setTitle("simple simon");
    }
    
    private void exitForm(java.awt.event.WindowEvent evt) {
        System.exit(0);
    }
}
