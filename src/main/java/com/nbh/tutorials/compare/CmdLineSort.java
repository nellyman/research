package com.nbh.tutorials.compare;

/**
 * Looks at the Arrays class.
 *
 * Also trying to make an Array variable throw a null pointer - no arrays have 
 * initialised variables in them, but didn't !!
 *
 *
 **/


import java.util.Arrays;
import java.util.Comparator;

public class CmdLineSort {
    
    static String[] memberString = new String[5];
    static boolean[] memberBoolean = new boolean[3];
    static int[] memberInt = new int[4];
    
   public static void main(String args[]) {
     int StrArray[] = new int[5];     
     printArray(args, "Before");
     Comparator comp = String.CASE_INSENSITIVE_ORDER; //optional !!
     Arrays.sort(args,comp);
     printArray(args, "After");
     
     System.out.println("Member arrays : String - "+memberString[0]+"  boolean - "+memberBoolean[0]+"  int - "+memberInt[0]);
     System.out.println("local static array : "+StrArray[0]);
     dataHolder dh = new dataHolder();     
     String[] data = dh.getData();
     System.out.println(data[0]);
   }
   private static void printArray(String array[], String header) {
     System.out.println("\n\n" + header);
     for (int i=0, n=array.length; i<n; i++) {
       System.out.println(i + ": " + array[i]);
     }
   }
}

class dataHolder{
 
    public String[] getData(){
        
        String[] data = new String[5];
        return data;
        
    }
    
}