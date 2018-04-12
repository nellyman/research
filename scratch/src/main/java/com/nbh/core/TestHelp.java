/*
 * TestHelp.java
 *
 * Created on 04 March 2004, 09:49
 */

package com.nbh.core;

/**
 *
 * @author  neal and rachel
 */
public class TestHelp implements TestIntf{
    
    /** Creates a new instance of TestHelp */
    public TestHelp() {
    }
    
    public synchronized static void  getHelp(){
        
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestHelp hlp = new TestHelp();
        System.out.println(hlp.testNonStaticField);
        //FinalTest.TryAndGetHello hello = new TryAndGetHello();
    }
    
}
