/*
 * MoveableObjectControl.java
 *
 * Created on 13 October 2002, 14:52
 *
 * Controls the movement of MoveableObjects..
 */

/**
 *
 * @author  neal and rachel
 */
public class MoveableObjectControl {
    
    private static int maxX=0;
    private static int maxY=0;

    /** Creates a new instance of MoveableObjectControl */
    public MoveableObjectControl() throws Exception{
        if (maxX==0 || maxY==0)
            throw new Exception("MaxX and MaxY values in MoveableObjectControl need setting");
        
    }

    public void moveObject(MoveableObject itemInMotion){
        
        int x = itemInMotion.getPos_x();        
        // move the objects across the screen, left to right....
        x=x+itemInMotion.getX_speed();
        if (x>maxX)
            itemInMotion.setX_speed(-(itemInMotion.getXSpeedFactor()));
        if (x<1)
            itemInMotion.setX_speed(+(itemInMotion.getXSpeedFactor()));
        itemInMotion.setPos_x(x);
        
        int y = itemInMotion.getPos_y();
        // move the object up / down the screen...
        y=y+itemInMotion.getY_speed();
        if (y>maxY)
            itemInMotion.setY_speed(-(itemInMotion.getYSpeedFactor()));
        if (y<1)
            itemInMotion.setY_speed(+(itemInMotion.getYSpeedFactor()));
         itemInMotion.setPos_y(y);        
    }
    
    public static void setMaxXPosn(int _maxX){
     
        maxX=_maxX;
    }
    public static void setMaxYPosn(int _maxY){
        
        maxY=_maxY;
    }
}
