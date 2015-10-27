/*
 * FinalTest.java
 *
 * Created on 04 March 2004, 09:56
 */

package com.nbh.tutorials;

/**
 *
 * @author  neal and rachel
 */
public  class FinalTest {
    
    private String hello="hello";
    
    class TryAndGetHello{
        
        private String hello1="hello again";
     
        public TryAndGetHello(){
           System.out.println( hello+"  "+hello1);
        }
    }
    
    /** Creates a new instance of FinalTest */
    public FinalTest() {
        TryAndGetHello h = new TryAndGetHello(); 
    }
    
    public static void main(String[] args){
     FinalTest t = new FinalTest();   
     TryAndGetHello hello = new TryAndGetHello();
    }
    
}
