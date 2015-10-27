/*
 * GameControl.java
 *
 * Created on 07 April 2003, 19:52
 */

package com.nbh.game.SimpleSimon;

/**
 *
 * @author  neal and rachel
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GameControl extends  java.awt.event.MouseAdapter{
    
    JPanel panel;
    
    /** Creates a new instance of GameControl */
    private GameControl() {
    }
    
    public GameControl(Container gamePanel){
      
        panel = (JPanel)gamePanel;
    }
    
    
    
    public void mouseClicked(MouseEvent evt){
        int x=evt.getX();
        int y=evt.getY();
       // c.checkLights(x,y);        
    }
}
