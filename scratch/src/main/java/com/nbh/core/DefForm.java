/*
 * DefForm.java
 *
 * Created on 13 April 2003, 18:59
 */

package com.nbh.core;

import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 *
 * @author  neal and rachel
 */
public class DefForm extends ArrayList{
    
    String Leftword;
    String Rightword;
    String Delimiter="::=";
    
    DefForm(String in){
        StringTokenizer del = new StringTokenizer(in, Delimiter);
        if (del.hasMoreTokens())
            Leftword=del.nextToken();
        if (del.hasMoreTokens())
            Rightword=del.nextToken();
        System.out.println("LeftWord is "+Leftword);
        System.out.println("RightWord is "+Rightword);
        
    }
    
    public static void main (String[] args){
        
        DefForm def = new DefForm("hello ::=world");
    }
}




