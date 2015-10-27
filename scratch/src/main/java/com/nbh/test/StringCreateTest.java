package com.nbh.test;

/**
 * @author uinxh
 *
 * Tests to see string vs Stringbuffer for string creation...
 * 
 */
public class StringCreateTest {

	static String startString = "This is going to be repeated many, many times !! ";
	static int times = 4550;

	public static void main(String[] args) {

		// String Test...
		StringBuffer buffer = new StringBuffer("");
		String string = "";
		
		long start = System.currentTimeMillis();
		
		for (int i = 0; i < times; i++) {
			string += (startString+i);
		}
		
		long stopStr = System.currentTimeMillis();

		for (int i = 0; i < times; i++) {
			buffer.append(startString+i);
		}
		long stop = System.currentTimeMillis();

		System.out.println("String- " + (stopStr - start) + " StringBuffer- " + (stop - stopStr));
	}
}
