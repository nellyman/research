/*
 * Created on 13-Dec-05
 *
 * Shows that locking an object doesn't lock member variables...
 * 
 */
package com.nbh.core.thread;

import java.util.ArrayList;
import java.util.List;

public class ObjectLockNotMemberVars {

	// variable
	private List myList = new ArrayList();

	public synchronized void lockTest(){
		
		System.out.println(" is the 'this' object locked? "+Thread.currentThread().holdsLock(this));
		
		System.out.println(" is the member variable locked ? "+Thread.currentThread().holdsLock(myList));
	}

	public static void main(String[] args) {
		
		ObjectLockNotMemberVars test = new ObjectLockNotMemberVars();
		test.lockTest();
		
	}
}
