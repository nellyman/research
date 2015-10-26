package com.nbh.tutorials.thread;

 
public class TestObjectRead implements Runnable{

	TestObject totalStuff;
	
	public TestObjectRead(TestObject obj){
		this.totalStuff = obj;
	}

	public void run(){
		while(true){
				countTotal(); // should pause
//				unlockedCountTotal(); // should return straight away..
				this.delay();
				this.delay();
				this.delay();
		}
	}
	
	private void delay(){
			try{
				// sleep a second...
				Thread.currentThread().sleep((long)(Math.random()*1000));
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
	}
	
	public synchronized void  countTotal(){
		System.out.println("working out locked total...");
		synchronized(totalStuff){
			System.out.println("locked size is : "+totalStuff.getSize());
		}	
	}
	
	public void unlockedCountTotal(){
		System.out.println("working out unlocked total...");		
			System.out.println("unlocked size is : "+totalStuff.getSize());				
	}



	public static void main(String[] args) {
		

		// Start three threads...
		TestObject obj = new TestObject();
		TestObjectRead child2 = new TestObjectRead(obj);
		TestObjectRead child3 = new TestObjectRead(obj);

		// create a thread for each child..
		Thread thread1 = new Thread(obj);
		Thread thread2 = new Thread(child2);
		Thread thread3 = new Thread(child3);

		// start them..
		thread1.start();
		thread2.start();
		thread3.start();
		
	}
	
	
	
}
