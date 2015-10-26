package image.alien;


/*
 * alien.java
 *
 * Created on 21 October 2002, 19:59
 *
 * An Alien object has -
 * a set choice of colours
 * Moves across the panel, then down 3 pixels and then moves back to the starting position
 * Has methods to test whether its been hit or not.
 * Could extend the MoveableObject class - we'd only need to write the draw of the class
 * MoveableObject should be updated, using a static set of classes and updating
 *
 *
 * @author  neal and rachel
 */

import javax.swing.*;
import java.awt.*;
import java.applet.*;
import java.util.*;

public class Alien {
    
    private int x,xSpeed=5,y,ySpeed=0;
    private int radius=5;
    private AlienControl control;
    boolean msgs=true;
    /** Creates a new instance of alien */
    public Alien(AlienControl _control){
        control = _control;
    }
    
    public void drawObject(Graphics g){
        // move the objects across the screen, left to right....
        WriteObjectToGraphics(g);
    }
    
    private void WriteObjectToGraphics(Graphics g){
        int tmpX=(x+10)*2;
        int tmpY=(y+10)*2;
        g.setColor(Color.green);
        g.fillOval(tmpX - radius, tmpY - radius, 2 * radius, 2 * radius);
        //g.drawString(""+x+","+y,x-(radius*2),y+15);
    }
    
    
    /** Getter for property x.
     * @return Value of property x.
     */
    public int getX() {
        return x;
    }
    
    /** Setter for property x.
     * @param x New value of property x.
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /** Getter for property y.
     * @return Value of property y.
     */
    public int getY() {
        return y;
    }
    
    /** Setter for property y.
     * @param y New value of property y.
     */
    public void setY(int y) {
        this.y = y;
    }
    
}
