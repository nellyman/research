package com.nbh.tutorials.thread;

public class AcceptCollection extends Thread{

	CollectionPlate collectionPlate=null;
	int contribution=0;
	String name;
	String msg="Contributing: current amount : ";

	public AcceptCollection(CollectionPlate cp, int contrib, String name){
		super();
		this.collectionPlate=cp;
		this.contribution=contrib;
		this.name=name;
	}

	public void run(){

		synchronized(collectionPlate){
			int amount=collectionPlate.amount+contribution;
			System.out.println(name+" "+msg+amount);
			collectionPlate.amount=amount;
			collectionPlate.notifyAll();

		}

	}
}
