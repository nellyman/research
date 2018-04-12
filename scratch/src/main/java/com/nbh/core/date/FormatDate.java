/*
 * FormatDate.java
 *
 * Created on 09 June 2003, 14:20
 */

package com.nbh.core.date;

import java.util.*;
import java.text.*;
/**
 *
 * @author  neal and rachel
 */
public class FormatDate {
    
    /** Creates a new instance of FormatDate */
    public FormatDate() {
        
          //DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);
          Locale[] dfs=DateFormat.getAvailableLocales();
          for (int i=0;i<dfs.length;i++){
           
              DateFormat df= DateFormat.getDateInstance(DateFormat.LONG,dfs[i]);
              System.out.println(i+"  "+dfs[i].getDisplayCountry()+"   "+df.format(new Date()));
              
          }
String dateFormat = "yyyy-MM-dd"; // = 1998-12-31
SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        System.out.println(dateFormatter.format(new Date()));          
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FormatDate fd=new FormatDate();
    }
    
}
