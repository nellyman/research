package com.nbh.tutorials.compare;


public class Name implements Comparable {
   private String lastName;
   private String firstName;
   
   public Name(String first, String last) {
     firstName = first;
     lastName = last;
   }
   
   public String getFirstName(){
    
       return firstName;
   }
   
   public String getLastName(){
    
       return lastName;
   }
   
   public int compareTo(Object o) {
     // This automatically throws exception if wrong type
     Name other = (Name)o;
     int returnValue = lastName.compareTo(other.lastName);  // String implments the comparible interface
     if (returnValue == 0) {
       returnValue = firstName.compareTo(other.firstName);
     }
     return returnValue;
   }

   public String toString() {
     return "Name[" + lastName + "," + firstName + "]";
   }
}
