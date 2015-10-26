/*
 * BinaryMathsListener.java
 *
 * Created on 16 April 2003, 11:46
 */

package com.nbh.gui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  neal and rachel
 */
public class BinaryMathsListener extends java.awt.event.MouseAdapter {
    
    BinaryArithmetic arithmetic;
    
    /** Creates a new instance of BinaryMathsListener */
    public BinaryMathsListener() {
    }
    public BinaryMathsListener(BinaryArithmetic _Arithmetic) {
        arithmetic = _Arithmetic;
    }
    
    public void mouseClicked(MouseEvent evt){
        
        String title="";
        Component cmp = evt.getComponent();
        if (cmp instanceof JButton){
            JButton buttonPressed =(JButton)cmp; 
            title = buttonPressed.getText();  
            short topNumber = arithmetic.getTopNumber();
            short lowerNumber = arithmetic.getLowerNumber();
            short bottomNumber = arithmetic.getResult();
            
            short results=0;
            if (title.equals("xor")){
                results = (short)(topNumber ^= lowerNumber);
                
            }
            if (title.equals("and")){
                results = (short)(topNumber &= lowerNumber);                
            }
            if (title.equals("or")){
                results = (short)(topNumber |= lowerNumber);                
            }            
            if (title.equals("<<")){
                results = (short)(bottomNumber << 1);                
            }            
            if (title.equals(">>")){
                results = (short)(bottomNumber >> 1);                
            }                        
            if (title.equals(">>>")){
                results = (short)(bottomNumber >>> 1);                
            }                        
            arithmetic.setResult(results);
        } 

    }
}