/*
 * ChildTest1.java
 *
 * Created on 04 June 2003, 11:50
 */

package com.nbh.tutorials;

/**
 *
 * @author  neal and rachel
 */
public class ChildTest1 extends com.nbh.tutorials.Parent {
    
    public int x=99;
    /** Creates a new instance of ChildTest1 */
    public ChildTest1() {
    }
    
    
    public String getMessage(){
     
        return ("Hello this is from ChildTest1");
    }
    public static void main(String[] args){
     
        Parent p;
        
        p=new ChildTest1();
        
        ChildTest1 ct1 = (ChildTest1)new Parent();
        System.out.println(ct1.getMessage());
    }
}
