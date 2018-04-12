/*
 * LoopTest.java
 *
 * Created on 12 May 2003, 09:51
 */

package com.nbh.core;

/**
 *
 * @author  neal and rachel
 */
public class LoopTest {
    
    /** Creates a new instance of LoopTest */
    public LoopTest() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int j=0;
        for (int i=0;i<10;i++){
         
            System.out.println("i is "+i);
            System.out.println("j is "+(++j));
        }
    }
    
}
