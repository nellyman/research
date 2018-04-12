/*
 * Locks 
 * 
 * 
 */
package com.nbh.core.thread;


public class ClassLockNotObject {

	public static void main(String[] args) {
		
			lockTest();
	}
	
	public static synchronized void lockTest(){
		
		ClassLockNotObject test = new ClassLockNotObject();
		
		System.out.println("Is it class object locked ? "+Thread.currentThread().holdsLock(test.getClass()));
		System.out.println("Is it object instance locked ? "+Thread.currentThread().holdsLock(test));		
	}
}
