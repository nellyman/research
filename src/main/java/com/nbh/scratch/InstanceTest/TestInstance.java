package com.nbh.scratch.InstanceTest;

public class TestInstance {

	private static String classString;
	private String instanceString;
	private int instanceInt;
	private long instanceLong;
	
	public void test(){
		if (instanceString==null){
			System.out.println("instanceString is null!");			
		}else{
			System.out.println("instanceString= '"+instanceString+"'");
		}
		
		System.out.println("instanceInt= '"+instanceInt+"'");
		System.out.println("instanceLong= '"+instanceLong+"'");
	}
	
	public static void main(String[] args){
		if (classString==null){
			System.out.println("classString is null!");			
		}else{
			System.out.println("classString= '"+classString+"'");
		}
		
		TestInstance instance =new TestInstance();
		instance.test();
		
	}

}

