/*
 * SortText.java
 *
 * Created on 06 May 2003, 11:40
 */

package com.nbh.gui;

/**
 *
 * @author  neal and rachel
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class SortText extends javax.swing.JPanel {
    
    private JTextArea ta;
    /** Creates a new instance of SortText */
    public SortText() {
        
        setLayout(new BorderLayout(10,10));
        ta =new JTextArea(10,30);
        JPanel tp = new JPanel(new FlowLayout());
        tp.add(ta);
        add(tp,BorderLayout.NORTH);
        JButton b = new JButton("sort");
        b.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent e){
                
                String[] words = readText(ta.getText());
                
                // words = com.nbh.tutorials.compare.UtilCompare.normalCompare(words);
                words = com.nbh.tutorials.compare.UtilCompare.comparitorCompare(words);
                ta.setText(reformText(words));
            }
        });
        
        JPanel p = new JPanel(new FlowLayout());
        p.add(b);
        add(p,BorderLayout.SOUTH);
    }
    
    public String[] readText(String text){
        
        StringTokenizer stk = new StringTokenizer(text);
        int arraySize = stk.countTokens();
        String[] words = new String[arraySize];
        int i=0;
        while (stk.hasMoreElements()){
            words[i++]=stk.nextToken();
        }
        return words;
    }
    
    public String reformText(String words[]){
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<words.length;i++){
            sb.append(words[i]+" ");
        }
        return (sb.toString());
    }
    
    public static void main(String[] args){
        SortText panel = new SortText();
        DefaultFrame frame = new DefaultFrame(panel, "text sort",false, 135,320);
        System.out.println(frame.getLayout().getClass().getName());
    }
}
