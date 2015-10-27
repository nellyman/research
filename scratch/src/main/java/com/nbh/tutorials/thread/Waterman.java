package com.nbh.tutorials.thread;
import java.util.*;

public class Waterman{



	public synchronized void fillCup(String name, WaterCup cup){

		// sync the cup.
		// delay + fill it up..
		// return...
		System.out.println("waterman: we have "+name+"'s cup");

		// provides a lock so that only one cup can be filled at once..
			System.out.println("waterman: starting to fill "+name+"'s cup");
			delay();
			cup.full=true;
			System.out.println("waterman: There you go little "+name);

	}


	public void delay(){
		try{
			// sleep a second...
			Thread.currentThread().sleep((long)(Math.random()*1000));
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}

}