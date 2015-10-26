package image.alien;

/*
 * alienControl.java
 *
 * Created on 21 October 2002, 20:28
 *
 * controls the deployment of Aliens !
 * @author  neal and rachel
 */

import image.alien.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.applet.*;

public class AlienControl {
    
    boolean msgs = true;
    int maxX=350,maxY=250,xSpeed=-5,ySpeed=5;
    int rows,cols;
    /** Creates a new instance of alienControl */
    private Alien[][] squad;
    
    // Constructor, x is number in a row, y is number of rows, xmax is maximum width, ymax is max height.
    
    public AlienControl(int _rows, int columns, int _xPos, int _yPos) {
        
        maxX=_xPos;
        maxY= _yPos;
        rows=_rows;
        cols=columns;
        //squad = new Vector(numberofAliens);      // change this from a vector to an arraylist ??
        squad=new Alien[rows][columns];
        if (msgs)
            System.out.println("At AlienControl, creation of aliens");
        
        // need initial values..

        int y=30;
        for (int row=0;row<rows;row++){
            int x=-40;
            for (int col=0;col<squad[row].length;col++){
                Alien a = new Alien(this);
                a.setX(x+=50);
                a.setY(y);
                squad[row][col]=a;
                if (msgs)
                    System.out.println("Created Alient number "+row+"  "+col);
            }// end col
            y+=30;
        } // end row
    }
    
    public void createSquad(int cols, int rows){
        
    }
    
    public void notifyBottomReached(image.alien.Alien victoriousAlien){
        
        if (msgs)
            System.out.println("Bottom reached !!");
        squad[victoriousAlien.getX()][victoriousAlien.getY()]=null;
    }
    
    public void DrawAliens(Graphics g){
        boolean doDropLine=false;
        for (int row=0;row<squad.length;row++){
            for (int col=0;col<squad[row].length;col++){
                Alien a = (Alien)squad[row][col];
                if (a!=null){
                    int x=a.getX();
                    System.out.println(x+" object new value of x = "+(x+xSpeed));
                    int tmpX=x+xSpeed;
                    if (tmpX>maxX){
                        xSpeed=-5;
                        System.out.println(x+" is bigger than "+maxX+" xSpeed is going to be "+xSpeed);
                        doDropLine=true;
                    }
                    if (tmpX<1){
                        xSpeed=+5;
                        System.out.println(x+" is smaller than 1.  xSpeed is going to be "+xSpeed);
                        doDropLine=true;
                    }
                    a.setX(x+xSpeed);
                    System.out.println(x+" setting x to "+a.getX());
                    int y=a.getY();
                    if (y>maxY){
                        this.notifyBottomReached(a);
                    }
                } // a !null
            }// col
        }// row
        if (doDropLine)
            this.dropALine();
        for (int row=0;row<squad.length;row++){
            for (int col=0;col<squad[row].length;col++){
                Alien a = (Alien)squad[row][col];
                if (a!=null)
                    a.drawObject(g);
            } // cols
        } // rows
    }
    
    public void dropALine(){
        System.out.println(" dropping a line");
        int ySpeed=5;
        for (int row=0;row<squad.length;row++){
            for (int col=0;col<squad[row].length;col++){
                Alien a = (Alien)squad[row][col];
                if (a!=null)
                    a.setY(a.getY()+ySpeed);
            } // col
        } //row
    }
}
