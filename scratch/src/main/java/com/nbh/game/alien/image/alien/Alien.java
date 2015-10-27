package com.nbh.game.alien.image.alien;


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

import java.awt.Color;
import java.awt.Graphics;

public class Alien {

    private int x;
    private final int xSpeed=5;
    private int y;
    private final int ySpeed=0;
    private final int radius=5;
    private final AlienControl control;
    boolean msgs=true;
    /** Creates a new instance of alien */
    public Alien(final AlienControl _control){
        this.control = _control;
    }

    public void drawObject(final Graphics g){
        // move the objects across the screen, left to right....
        this.WriteObjectToGraphics(g);
    }

    private void WriteObjectToGraphics(final Graphics g){
        final int tmpX=(this.x+10)*2;
        final int tmpY=(this.y+10)*2;
        g.setColor(Color.green);
        g.fillOval(tmpX - this.radius, tmpY - this.radius, 2 * this.radius, 2 * this.radius);
        //g.drawString(""+x+","+y,x-(radius*2),y+15);
    }


    /** Getter for property x.
     * @return Value of property x.
     */
    public int getX() {
        return this.x;
    }

    /** Setter for property x.
     * @param x New value of property x.
     */
    public void setX(final int x) {
        this.x = x;
    }

    /** Getter for property y.
     * @return Value of property y.
     */
    public int getY() {
        return this.y;
    }

    /** Setter for property y.
     * @param y New value of property y.
     */
    public void setY(final int y) {
        this.y = y;
    }

}
