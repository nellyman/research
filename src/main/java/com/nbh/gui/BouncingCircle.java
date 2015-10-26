package com.nbh.gui;

import java.awt.*;
import java.awt.Graphics.*;
import java.applet.*;

public class BouncingCircle extends Applet implements Runnable{

private Color currColor=Color.green;		// Current circle colour.
private int yPos=2, size=40,xPos;
private int xSize=300,ySize=300;
private boolean FALL=true;
private Thread myThread=null, myThread2=null;

public void init(){

	setSize(300,300);		// Set the FrameSize

}

public void start(){

	myThread=new Thread(this);

	for (int i=0;i<5;i++){

		Thread t=new Thread(myThread);

		xPos=80+(i*20);
		t.start();
	}

	/*
	myThread.start();
	xPos+=50;
	myThread2=new Thread(this);
	myThread2.start();
	*/
}


public void run(){


	// while (myThread==tempThread){
	while (true){

		Thread tempThread=Thread.currentThread();

		System.out.println(tempThread.getName());
		if (FALL==false){			// going up the screen !!
			yPos-=2;
			if (yPos<2){			// Check if at the top.
				yPos=2;
				FALL=true;
			}
		}
		if (FALL==true){
			yPos+=2;
			if (yPos>(ySize-(size+2))){		//Here we make the circles fall down the screen.
				yPos=ySize-(size+2);
				FALL=false;
			}
		}


		try{
			Thread.sleep(40);
		}
		catch (InterruptedException e){}
		repaint();
	}
}

public void paint(Graphics g){

	g.drawOval(xPos,yPos,size,size);
	g.setColor(currColor);
	g.fillOval(xPos,yPos,size,size);
	g.setColor(Color.white);
	g.fillOval((xPos+size/4),(yPos+size/4),(size/4),(size/4));
}

public synchronized void stop(){

	myThread=null;
}


}


