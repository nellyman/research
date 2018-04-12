package com.nbh.gui.game.alien.image.alien;

/*
 * alienControl.java
 *
 * Created on 21 October 2002, 20:28
 *
 * controls the deployment of Aliens !
 * @author  neal and rachel
 */

import java.awt.Graphics;

public class AlienControl {

    boolean msgs = true;
    int maxX=350,maxY=250,xSpeed=-5,ySpeed=5;
    int rows,cols;
    /** Creates a new instance of alienControl */
    private final Alien[][] squad;

    // Constructor, x is number in a row, y is number of rows, xmax is maximum width, ymax is max height.

    public AlienControl(final int _rows, final int columns, final int _xPos, final int _yPos) {

        this.maxX=_xPos;
        this.maxY= _yPos;
        this.rows=_rows;
        this.cols=columns;
        //squad = new Vector(numberofAliens);      // change this from a vector to an arraylist ??
        this.squad=new Alien[this.rows][columns];
        if (this.msgs) {
            System.out.println("At AlienControl, creation of aliens");
        }

        // need initial values..

        int y=30;
        for (int row=0;row<this.rows;row++){
            int x=-40;
            for (int col=0;col<this.squad[row].length;col++){
                final Alien a = new Alien(this);
                a.setX(x+=50);
                a.setY(y);
                this.squad[row][col]=a;
                if (this.msgs) {
                    System.out.println("Created Alient number "+row+"  "+col);
                }
            }// end col
            y+=30;
        } // end row
    }

    public void createSquad(final int cols, final int rows){

    }

    public void notifyBottomReached(final Alien victoriousAlien){

        if (this.msgs) {
            System.out.println("Bottom reached !!");
        }
        this.squad[victoriousAlien.getX()][victoriousAlien.getY()]=null;
    }

    public void DrawAliens(final Graphics g){
        boolean doDropLine=false;
        for (final Alien[] element : this.squad) {
            for (final Alien a : element) {
                if (a!=null){
                    final int x=a.getX();
                    System.out.println(x+" object new value of x = "+(x+this.xSpeed));
                    final int tmpX=x+this.xSpeed;
                    if (tmpX>this.maxX){
                        this.xSpeed=-5;
                        System.out.println(x+" is bigger than "+this.maxX+" xSpeed is going to be "+this.xSpeed);
                        doDropLine=true;
                    }
                    if (tmpX<1){
                        this.xSpeed=+5;
                        System.out.println(x+" is smaller than 1.  xSpeed is going to be "+this.xSpeed);
                        doDropLine=true;
                    }
                    a.setX(x+this.xSpeed);
                    System.out.println(x+" setting x to "+a.getX());
                    final int y=a.getY();
                    if (y>this.maxY){
                        this.notifyBottomReached(a);
                    }
                } // a !null
            }// col
        }// row
        if (doDropLine) {
            this.dropALine();
        }
        for (final Alien[] element : this.squad) {
            for (final Alien a : element) {
                if (a!=null) {
                    a.drawObject(g);
                }
            } // cols
        } // rows
    }

    public void dropALine(){
        System.out.println(" dropping a line");
        final int ySpeed=5;
        for (final Alien[] element : this.squad) {
            for (final Alien a : element) {
                if (a!=null) {
                    a.setY(a.getY()+ySpeed);
                }
            } // col
        } //row
    }
}
