package com.nbh.tutorials.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uinxh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestObject implements Runnable{

	List totalStuff = new ArrayList();
	
	public void addSomething(){
		synchronized(totalStuff){
			// add something
			totalStuff.add("item");
		}
		
	}
	
	
	public int getSize(){
		return totalStuff.size();
	}
	

	
	public void run(){
		while(true){
			addSomething();
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

}
