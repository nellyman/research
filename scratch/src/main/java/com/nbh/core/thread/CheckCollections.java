package com.nbh.core.thread;

public class CheckCollections extends Thread{


	CollectionPlate collectionPlate = null;

	public CheckCollections(CollectionPlate collectionPlate){
		super();
		this.collectionPlate = collectionPlate;
	}

	public void run(){

		//check amount of money if >100 then release it so threads can run...

		synchronized (collectionPlate){
			while(collectionPlate.amount <100){

				try{

					System.out.println("IS THAT IT !! waiting !!");
					collectionPlate.wait();
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}

			// means 100 has been met..
			System.out.println("thanks !!");

		}
	}
}