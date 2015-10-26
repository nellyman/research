package com.nbh.tutorials.thread;

public class Child implements Runnable{

	IceCreamMan icecreamMan;
	IceCreamDish dish = new IceCreamDish();
	String name;

	public static void main(String args[]){

		// create an icecream man...
		IceCreamMan george = new IceCreamMan();

		// start him up...
		george.start();

		// create some child objects...
		Child child1= new Child("Ricardo");
		Child child2= new Child("Sally");
		Child child3= new Child("Maria");
		child1.icecreamMan = george;
		child2.icecreamMan = george;
		child3.icecreamMan = george;

		// create a thread for each child..
		Thread thread1 = new Thread(child1);
		Thread thread2 = new Thread(child2);
		Thread thread3 = new Thread(child3);

		// start them..
		thread1.start();
		thread2.start();
		thread3.start();
	}

	public void run(){

		//receive icecream
		getIceCream();
		//eat icecream...
		eatIceCream();
	}

	public void getIceCream(){

		// try to give this child's dish to ice cream man...
		if (!Thread.currentThread().holdsLock(icecreamMan)){

			System.out.println("waiting for ice cream man !!!");
		}
		synchronized(icecreamMan){

			icecreamMan.requestIceCream(dish);
		}

	}

	public void eatIceCream(){

		synchronized(dish){
			while(dish.readyToEat==false){
				try{
					System.out.println("waiting for ice cream man to finish...");
					dish.wait();
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}
			dish.notifyAll();
		}
		System.out.println(name+" yum !!");
	}

	public Child(String name){
		this.name=name;
	}

}