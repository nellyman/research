package com.nbh.tutorials.compare;

import java.util.*;

public class SortName {
    public static void main(String args[]) {
        Name names[] = {
            new Name("John", "Doe"),
            new Name("Jane", "Doe"),
            new Name("Mary", "Jane"),
            new Name("Mary", "Doe"),
            new Name("John", "Smith"),
            new Name("Mary", "Smith"),
        };
        printArray(names, "Before");
        Arrays.sort(names);
        printArray(names, "After");
        
        Comparator comp = new Comparator(){
            public int compare(Object one, Object two){
                Name name1 = (Name)one;
                Name name2 = (Name)two;
                int retVal = name1.getFirstName().compareTo(name2.getFirstName());
                if (retVal==0)
                      name1.getLastName().compareTo(name2.getLastName());
                
                return retVal;
            }
        };
        
        Arrays.sort(names,comp);
        printArray(names,"After first name comparator search..");
        
        
    }
    
    private static void printArray(Name array[], String header) {
        System.out.println("\n\n" + header);
        for (int i=0, n=array.length; i<n; i++) {
            System.out.println(i + ": " + array[i]);
        }
    }
    
    
}


