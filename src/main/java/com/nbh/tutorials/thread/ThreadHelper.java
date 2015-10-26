
package com.nbh.tutorials.thread;


public class ThreadHelper {


	/*
	 * delays a thread...
	 */
	static void delay(){
		try{
			// sleep a second...
			Thread.currentThread().sleep((long)(Math.random()*1000));
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
}
