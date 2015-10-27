package com.nbh.tutorials.thread;

import java.util.*;

public class ThirstyChild implements Runnable{


	WaterCup cup = new WaterCup();
	String name;
	Waterman waterMan;

	public ThirstyChild(String name, Waterman waterMan){
		this.name=name;
		this.waterMan=waterMan;
	}


	public void run(){

		while(true){

			// get water...
			this.getWater();

			// drink water...
			this.drinkWater();
			delay();
		}

	}

	public void getWater(){
		System.out.println(name+": I'm thirsty, getting some water, I may have to wait...");
		waterMan.fillCup(name,cup);	
	}


	public void drinkWater(){

		System.out.println(name+": drinking water...");
		delay();
		cup.full=false;
		System.out.println(name+": ahh, done.");
	}


	public void delay(){
		try{
			// sleep a second...
			Thread.currentThread().sleep((long)(Math.random()*1000));
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}


	public static void main(String[] args){

		// create waterman...
		Waterman waterMan= new Waterman();

		// create 3 children...
		ThirstyChild child1 = new ThirstyChild("Tommy", waterMan);
		ThirstyChild child2 = new ThirstyChild("Joey", waterMan);
		ThirstyChild child3 = new ThirstyChild("Billy", waterMan);

		// create a thread for each child..
		Thread thread1 = new Thread(child1);
		Thread thread2 = new Thread(child2);
		Thread thread3 = new Thread(child3);

		// start them..
		thread1.start();
		thread2.start();
		thread3.start();

	}

}