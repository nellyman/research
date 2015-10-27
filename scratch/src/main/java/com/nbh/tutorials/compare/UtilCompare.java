/*
 * UtilCompare.java
 *
 * Created on 06 May 2003, 12:04
 */

package com.nbh.tutorials.compare;

/**
 *
 * @author  neal and rachel
 */

import java.util.*;

public class UtilCompare {
    
    
    /** Creates a new instance of UtilCompare */
    public UtilCompare() {
    }
    
    /**
     * Although a SOrtedMap would probably do this for me, its an exercise...(!)
     **/
    public static String[] normalCompare(String[] o){
        Arrays.sort(o);
        return (o);
    }
    public static String[] comparitorCompare(String[] o){
                
        Comparator comp = new Comparator(){
            public int compare(Object one, Object two){
                String name1 = (String)one;
                String name2 = (String)two;
                int retVal=0;
                if (name1.startsWith("!"))      // this causes problems as equals() starts to break down -interface not a stable interface !!
                    retVal=1;
                else
                    retVal = name1.compareTo(name2);             
                return retVal;
            }
        };
        Arrays.sort(o,comp);
        return o;
    }    
}
