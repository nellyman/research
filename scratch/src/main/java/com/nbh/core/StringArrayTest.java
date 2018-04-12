package com.nbh.core;
import java.io.*;

public class StringArrayTest{


public static void main(String[] args){

try{
	String str="the cat sat on the mat";
	byte [] bytes = str.getBytes();
	ByteArrayOutputStream op = new ByteArrayOutputStream(100);
	op.write(bytes,0,bytes.length);
	String result = op.toString("UTF-8");
	System.out.println("I think "+result+" is what str is !!");
}
catch(Exception e){
	e.printStackTrace();
}

}


}
