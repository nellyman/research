/*
 * TestExceptions.java
 *
 * Created on 30 April 2003, 11:07
 */

package com.nbh.core;

/**
 *
 * @author  neal and rachel
 */
import java.io.*;
public class TestExceptions {
    
    /** Creates a new instance of TestExceptions */
    public TestExceptions() throws RuntimeException{
        System.out.println("HEllo");
    }
    public void msg()throws java.io.IOException{        
        File f = new File("he.txt");
    }
    public void msg1() throws RuntimeException{
     
        System.out.println("jknwde");
    }
    
    public static void main(String[] args){
     
        TestExceptions ex= new TestExceptions();
        ex.msg1();
    }
    
    
}
