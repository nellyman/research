package com.nbh.game.alien;

/*
 * MoveableObject.java
 *
 * Created on 13 October 2002, 14:21
 */

/**
 *
 * @author  neal and rachel
 */

import java.awt.Color;
import java.awt.Graphics;

public class MoveableObject {

    //private Color color =new Color(Color.red);
    private final int radius=10;
    private int pos_x=50,pos_y=50;
    private int x_speed=1,y_speed=0,xSpeedFactor=this.x_speed,ySpeedFactor=this.y_speed;
    private final MoveableObjectControl control;
    private boolean msgsOn=false;

    public MoveableObject(final int x, final int y, final int _xSpeed, final int _ySpeed) throws Exception{
        this.control = new MoveableObjectControl();
        this.pos_x=x;
        this.pos_y=y;
        this.x_speed=_xSpeed;
        this.y_speed=_ySpeed;
        this.xSpeedFactor=this.x_speed;
        this.ySpeedFactor=this.y_speed;
    }

    public void DrawObject(final Graphics g){
        this.control.moveObject(this);
        this.WriteObjectToGraphics(g);
    }

    private void WriteObjectToGraphics(final Graphics g){
        g.setColor (Color.red);
        g.fillOval (this.pos_x - this.radius, this.pos_y - this.radius, 2 * this.radius, 2 * this.radius);
        if (this.msgsOn) {
            g.drawString(""+this.pos_x+"  "+this.pos_y,this.pos_x+(this.radius*2),this.pos_y+10);
        }
    }

    public void setMessages(final boolean msgs){
        this.msgsOn=msgs;
    }

    /** Getter for property pos_x.
     * @return Value of property pos_x.
     */
    public int getPos_x() {
        return this.pos_x;
    }

    /** Setter for property pos_x.
     * @param pos_x New value of property pos_x.
     */
    public void setPos_x(final int pos_x) {
        this.pos_x = pos_x;
    }

    /** Getter for property pos_y.
     * @return Value of property pos_y.
     */
    public int getPos_y() {
        return this.pos_y;
    }

    /** Setter for property pos_y.
     * @param pos_y New value of property pos_y.
     */
    public void setPos_y(final int pos_y) {
        this.pos_y = pos_y;
    }

    /** Getter for property x_speed.
     * @return Value of property x_speed.
     */
    public int getX_speed() {
        return this.x_speed;
    }

    /** Setter for property x_speed.
     * @param x_speed New value of property x_speed.
     */
    public void setX_speed(final int x_speed) {
        this.x_speed = x_speed;
    }

    /** Getter for property y_speed.
     * @return Value of property y_speed.
     */
    public int getY_speed() {
        return this.y_speed;
    }

    /** Setter for property y_speed.
     * @param y_speed New value of property y_speed.
     */
    public void setY_speed(final int y_speed) {
        this.y_speed = y_speed;
    }
    public int getXSpeedFactor(){
        return this.xSpeedFactor;
    }
    public int getYSpeedFactor(){
        return this.ySpeedFactor;
    }
}
