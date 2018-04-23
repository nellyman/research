package com.nbh.core.thread;


public class Minister{

	CollectionPlate collectionPlate = new CollectionPlate();

	public static void main(String[] args){

		Minister minister = new Minister();
		CheckCollections cc= new CheckCollections(minister.collectionPlate);
		cc.start();

		// accept new contributions..

		for (int i=0;i<6;i++){

			AcceptCollection ac = new AcceptCollection(minister.collectionPlate,20,"contribution"+i);
			ac.start();
		}
	}



}