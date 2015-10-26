/*
 * BinaryArthimetic.java
 *
 * Created on 16 April 2003, 10:36
 *
 * notes -
 *
 * JLabel is extended so that I can use get an ID for it. 
 * Could have used setName as the row is the only id used now, (would perform the change).
 * 
 * An Array of the labels is created and listeners are attached so that clicking on the label
 * will turn a 1 to 0 and 0 to 1.
 *
 * Buttons are added at the bottom and these have a single MathsListener that will interagate 
 * the button pressed and perform actions based on it.
 * 
 */

package com.nbh.gui;

/**
 *
 * @author  neal and rachel
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class BinaryArithmetic extends JPanel {
    
    public static final int SIZE=8;
    public static final int RESULTS = 2;
    public static final int UPPER = 0;
    public static final int LOWER = 1;
    BinaryLabel[][] binElements = new BinaryLabel[3][SIZE];
    BinaryLabel[] totals = new BinaryLabel[3];
    
    /** Creates a new instance of BinaryArthimetic */
    public BinaryArithmetic() {
        
        JPanel topVals = this.createByte(UPPER,(totals[UPPER]=new BinaryLabel("0")), binElements[0], true);
        JPanel lowerVals = this.createByte(LOWER,(totals[LOWER]=new BinaryLabel("0")), binElements[1], true);
        
        
        JPanel DigitsLayout = new JPanel();
        DigitsLayout.setLayout(new GridLayout(5,1));
        
        DigitsLayout.add(topVals); 
        DigitsLayout.add(lowerVals);
        
        BinaryMathsListener listener = new BinaryMathsListener(this); 
        JPanel buttonPanel = new JPanel(new GridLayout(1,7));
        JButton annd = new JButton("and");
        annd.addMouseListener(listener);
        buttonPanel.add(annd);
        JButton oor = new JButton("or");
        oor.addMouseListener(listener);
        buttonPanel.add(oor);
        JButton xoor = new JButton("xor");
        xoor.addMouseListener(listener);
        buttonPanel.add(xoor);
        
       
        DigitsLayout.add(buttonPanel);
        
        JPanel results = this.createByte(RESULTS,(totals[RESULTS]=new BinaryLabel("0")), binElements[2], false);
        DigitsLayout.add(results);

       
        JPanel lowerOptions = new JPanel();
        
       
        JButton lleft = new JButton("<<");
        lleft.addMouseListener(listener);
        lowerOptions.add(lleft);
        JButton right = new JButton(">>");
        right.addMouseListener(listener);
        lowerOptions.add(right);
        JButton rright = new JButton(">>>");
        rright.addMouseListener(listener);
        lowerOptions.add(rright);
        
        DigitsLayout.add(lowerOptions);
        
        
        this.setLayout(new BorderLayout(10,10));
        
        this.add(BorderLayout.NORTH, DigitsLayout);
    }
    
    public short getTopNumber(){
        
        return (getNumber(UPPER));
    }
    
    public short getResult(){
     
        return (getNumber(RESULTS));
    }
    
    public short getLowerNumber(){
        
        return (getNumber(LOWER));
    }
    
    private short getNumber(int row){
        String value = "";
        for (int i=0;i<SIZE;i++){
            String labelVal = binElements[row][i].getText();
            value=value+labelVal;
        }
        return (Short.parseShort(value,2));
    }
    
    private void setResultTotal(short total){
     
        totals[RESULTS].setText(Short.toString(total));        
    }
    
    public void setResult(short result){
        
        int mask = 128;
        for (int i=0;i<SIZE;i++){
            
            if ((short)(result & mask)!=0)
                setElement("1", RESULTS, i);
            else
                setElement("0", RESULTS, i);
            mask >>= 1;
        }
        setResultTotal(result);        
        this.repaint();
    }
        
        private void setElement(String value, int row, int col){
            binElements[row][col].setText(value);
        }
        
        public JPanel createByte(int row, BinaryLabel total, BinaryLabel[] digits, boolean listener){
            
            JPanel results = new JPanel(new GridLayout(1,digits.length+1));
            
            Border b = BorderFactory.createEtchedBorder();
            
            for (int i=0; i<digits.length;i++){
                
                digits[i] = new BinaryLabel("0");
                digits[i].setID(row, i);
                digits[i].setBorder(b);
                digits[i].setHorizontalAlignment(JLabel.CENTER);
                digits[i].setVerticalAlignment(JLabel.CENTER);
                
                if (listener ){
                    digits[i].addMouseListener(new MouseAdapter(){
                        BinaryLabel labelPressed=null;
                        public void mouseClicked(MouseEvent evt){
                            Component cmp = evt.getComponent();
                            if (cmp instanceof BinaryLabel){
                                labelPressed =(BinaryLabel)cmp;
                                String title = labelPressed.getText();
                                int row = labelPressed.getRow();
                                int col = labelPressed.getCol();
                                byte value = Byte.parseByte(labelPressed.getText());
                                String newValue = "0";
                                if (value==0)
                                    newValue = "1";
                                labelPressed.setText(newValue);
                                short total = getNumber(row);
                                totals[row].setText(Short.toString(total));
                            }
                        }
                    });
                    
                }
                results.add(digits[i]);
            }
            total.setBorder(b);           
            total.setHorizontalAlignment(JLabel.CENTER);
            total.setVerticalAlignment(JLabel.CENTER);
            results.add(total);
            return results;
        }
        
        
        public static void main(String[] args){
            
            BinaryArithmetic panel = new BinaryArithmetic();
            DefaultFrame frame = new DefaultFrame(panel, "binary arithmetic",false, 115,300);
            
        }
        
    }
