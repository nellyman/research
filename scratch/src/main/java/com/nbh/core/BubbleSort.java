package com.nbh.core;

public class BubbleSort {

	int size=5;
   	int random, flag, temp;  int numarray[] = new int[size]; /* declares a new integer array holding from 0 to 99 */
   	char letarray[] = new char[size]; /* declares a new character array holding 0 to 99 */
   	char tamp;
   	boolean test=false;


   public void performSort(){

	  do { /* do...while loop to sort the array */
	  	flag=0;
	  	for(int j=0; j<(size-1); j++)    {
			if(numarray[j] > numarray[j+1])      {
				temp = numarray[j];
				tamp = letarray[j];
				numarray[j] = numarray[j+1];
				numarray[j+1] = temp;
				letarray[j] = letarray[j+1];
				letarray[j+1] = tamp;
				flag = 1;
			}
			if (test){
				getResults();
			}
		}
	  } while(flag!=0); /* end of bubble sort */


   }

   public void getResults(){
	   System.out.println(printArray());

   }

   private String printArray(){

	String op="";
	for(int k=0; k<size; k++) /* print sorted array */   {
   		  op+=(""+ letarray[k] + " ");
	}

	return op;
   }


   public void performTest(){

	test=true;

	// setup data...
	int index=0;
	while (index<(size-1)){
  		random = (int)(Math.random()*90) + 1;
  		if (random <99 && random >= 65)      {
			numarray[index] = random;
			letarray[index] = (char)random;
			index++;
		}
	}

	// print unsorted...
	System.out.println("unsorted: "+this.printArray()+"\r\n\r\n");

	// do sort...
	this.performSort();

	// print sorted..
	System.out.println("sorted: "+this.printArray()+"\r\n\r\n");


   }

  public static void main(String[] args) {

	BubbleSort sort= new BubbleSort();
	sort.performTest();
  }

}
