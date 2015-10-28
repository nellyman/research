package com.nbh.gui;

import javax.swing.JOptionPane;

public class SimpleDialog {

    public static void main( final String[] args ){
        String name = "";
        name=JOptionPane.showInputDialog("Please enter final your name");
        final String msg = "Hello " + name + "!";
        JOptionPane.showMessageDialog(null, msg);
    }
}