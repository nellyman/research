/*
 * StdOutRedirect.java
 *
 * Created on 15 April 2003, 09:57
 */

package com.nbh.util;

import javax.swing.*;
import java.io.PrintStream;

/**
 *
 * @author  neal and rachel
 */
public class StdOutRedirect extends PrintStream {
    
    StdOutPanel display;
    
    /** Creates a new instance of StdOutRedirect */
    public StdOutRedirect(PrintStream out1, StdOutPanel _display) {
        super(out1);
        display=_display;
    }
    
    // All writes to this print stream are copied to two print streams
    
    public void write(byte buf[], int off, int len) {
        try {
            super.write(buf, off, len);
        } catch (Exception e) {
        }
    }
    
    public void println(String msg){
        try{
            super.println(msg);
            display.setMessage(msg);
            
        }catch (Exception e){            
        }        
    }
    
    
    public void flush() {
        super.flush();
    }
    
    public static void main(String[] args) {
        
        StdOutPanel p = new StdOutPanel();
        StdOutRedirect std = new StdOutRedirect(System.out, p);
        com.nbh.gui.DefaultFrame pt = new com.nbh.gui.DefaultFrame(p,"system . out",true);
        
        std.println("Hello world ");
        std.println("This is a message !! ");
        
        Thread t = new Thread(Thread.currentThread());
        try{
          t.sleep(2000);
        }
        catch(java.lang.InterruptedException ie){
         
            std.println(ie.getMessage());
        }
        std.println("And some more !! ");
        std.println("And some more 1 ");
        std.println("And some more 2 ");
        std.println("And some more 3 ");
        std.println("And some more 4 ");
        std.println("And some more 5 ");
        std.flush();
        
    }
}

class StdOutPanel extends JPanel{
    int incY=10;
    
    JTextArea textArea=new JTextArea();
          
    public StdOutPanel(){
        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        this.add(textArea);
    }
    
      
    public void setMessage(String _msg){        
        textArea.append(_msg+"\n");
    }
    
}
