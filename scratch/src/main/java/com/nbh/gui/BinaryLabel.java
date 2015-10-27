/*
 * BinaryLabel.java
 *
 * Created on 16 April 2003, 11:02
 */

package com.nbh.gui;

/**
 *
 * @author  neal and rachel
 */
public class BinaryLabel extends javax.swing.JLabel {
    
    int col;
    int row;
    
    public BinaryLabel(){
     
        super();
    }
    public BinaryLabel(String title){
        super(title);
    }
    
    public void setID(int _row, int _col){
        
        row = _row;
        col = _col;
    }
    public int getRow(){
        
        return row;
    }
    public int getCol(){
        
        return col;
    }
}
