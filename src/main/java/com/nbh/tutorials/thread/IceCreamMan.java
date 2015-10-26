package com.nbh.tutorials.thread;

import java.util.*;

public class IceCreamMan extends Thread{


	private Vector dishes=new Vector();


	public void run(){

		while(true){
			if (!dishes.isEmpty()){
				System.out.println("Icecream man has a client");
				serveIceCream();
			}else{
				try{
					System.out.println("IceCream:does not have a client");
					// sleep a second...
					Thread.currentThread().sleep(1000);
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}
		}
	}


	public void serveIceCream(){
		//get dish...
		IceCreamDish currentDish = (IceCreamDish)dishes.get(0);

		//random wait...
		if (Math.random()>.5) delay();

		//if dish not null, then fill it...
		if (currentDish!=null){
			synchronized(currentDish){
				currentDish.readyToEat=true;
				System.out.println("IceCream is ready !!");
				currentDish.notifyAll();
				dishes.remove(currentDish);
			}

		}
	}

	public void requestIceCream(IceCreamDish dish){
		dishes.add(dish);
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