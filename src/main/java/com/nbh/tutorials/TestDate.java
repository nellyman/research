/*
 * TestDate.java
 *
 * Created on 28 April 2003, 10:54
 */

package com.nbh.tutorials;

/**
 *
 * @author  neal and rachel
 */

import java.util.*;
import java.text.*;

public class TestDate {
    
    /** Creates a new instance of TestDate */
    public TestDate(String date) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM dd, yyyy hh:mm:ss aaa");
        
        try{
         sdf.parse(date);
         Calendar c = sdf.getCalendar();
        System.out.println(c.toString());        
        
        }catch (ParseException e){
            
         System.out.println(e.getMessage());   
        }
    }
    
    public static void main(String[] args){
     
        TestDate td  = new TestDate("Friday, April 18, 2003 9:47:22 PM");
        
    }
}
