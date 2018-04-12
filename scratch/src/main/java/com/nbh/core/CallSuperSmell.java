package com.nbh.core;


public class CallSuperSmell{


	public void somethingImportant(){
		System.out.println("Processing something super-important");
	}


	public void doSomethingCorrectly(){
		System.out.println("super-doSomethingCorrectly !!");
		processingRequiredBySubClasses();
	}

	public void processingRequiredBySubClasses(){
		// meant to be empty..
	}


	public static void main(String[] args){

		System.out.println("We're going to call a sub-class'es method. What happens");
		System.out.println(" is that we've forgotten to put in the super call in the ");
		System.out.println(" second iteration of it. This leads to missing super processing");
		System.out.println(" Third iteration uses methods on the super class to set provide ");
		System.out.println(" sub-class processing.");

		CallSubSmell subSmell = new CallSubSmell();

		System.out.println("1st ...");
		subSmell.somethingImportantCorrect();
		System.out.println("2nd ...");
		subSmell.somethingImportant();
		System.out.println("3rd ...");
		subSmell.doSomethingCorrectly();


	}
}




class CallSubSmell extends CallSuperSmell{

	public void somethingImportant(){
		System.out.println("Processing something sub-important");
	}

	public void somethingImportantCorrect(){
		super.somethingImportant();
		System.out.println("Processing something sub-important");
	}

	public void processingRequiredBySubClasses(){
		System.out.println("Processing something in sub-class !!");
	}

}
