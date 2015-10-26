/*
 * MoveableObject.java
 *
 * Created on 13 October 2002, 14:21
 */

/**
 *
 * @author  neal and rachel
 */

import javax.swing.*;
import java.awt.*;
import java.applet.*;

public class MoveableObject {

    //private Color color =new Color(Color.red);
    private int radius=10;
    private int pos_x=50,pos_y=50;
    private int x_speed=1,y_speed=0,xSpeedFactor=x_speed,ySpeedFactor=y_speed;
    private MoveableObjectControl control;
    private boolean msgsOn=false;
    
    public MoveableObject(int x, int y, int _xSpeed, int _ySpeed) throws Exception{
        control = new MoveableObjectControl();
        pos_x=x;
        pos_y=y;
        x_speed=_xSpeed;
        y_speed=_ySpeed;
        xSpeedFactor=x_speed;
        ySpeedFactor=y_speed;
    }
    
    public void DrawObject(Graphics g){
        control.moveObject(this);
        WriteObjectToGraphics(g);
    }

    private void WriteObjectToGraphics(Graphics g){
        g.setColor (Color.red);
        g.fillOval (pos_x - radius, pos_y - radius, 2 * radius, 2 * radius);
        if (msgsOn)
            g.drawString(""+pos_x+"  "+pos_y,pos_x+(radius*2),pos_y+10);
    }
    
    public void setMessages(boolean msgs){
        msgsOn=msgs;
    }
    
    /** Getter for property pos_x.
     * @return Value of property pos_x.
     */
    public int getPos_x() {
        return pos_x;
    }
    
    /** Setter for property pos_x.
     * @param pos_x New value of property pos_x.
     */
    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }
    
    /** Getter for property pos_y.
     * @return Value of property pos_y.
     */
    public int getPos_y() {
        return pos_y;
    }
    
    /** Setter for property pos_y.
     * @param pos_y New value of property pos_y.
     */
    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }
    
    /** Getter for property x_speed.
     * @return Value of property x_speed.
     */
    public int getX_speed() {
        return x_speed;
    }
    
    /** Setter for property x_speed.
     * @param x_speed New value of property x_speed.
     */
    public void setX_speed(int x_speed) {
        this.x_speed = x_speed;
    }
    
    /** Getter for property y_speed.
     * @return Value of property y_speed.
     */
    public int getY_speed() {
        return y_speed;
    }
    
    /** Setter for property y_speed.
     * @param y_speed New value of property y_speed.
     */
    public void setY_speed(int y_speed) {
        this.y_speed = y_speed;
    }
    public int getXSpeedFactor(){
        return xSpeedFactor;
    }
    public int getYSpeedFactor(){
        return ySpeedFactor;
    }
}
