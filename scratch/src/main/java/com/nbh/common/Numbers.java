
package com.nbh.common;


 public class Numbers{

	    public int getRandom(int range) {

	        double s=Math.abs(Math.random()) * range;

	        return (new Double(s).intValue());
	    }



}
