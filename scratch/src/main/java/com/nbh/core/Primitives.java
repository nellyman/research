/*
 * Primitives.java
 *
 * Created on 09 April 2003, 14:08
 */

package com.nbh.core;

/**
 *
 * @author  neal and rachel
 */
public class Primitives {
    
    /** Creates a new instance of Primitives */
    public Primitives() {
    }
    
    public static void test(int a, int b){

     System.out.println(" a = "+a+" "+Integer.toBinaryString(a)+"   b = "+b+" "+Integer.toBinaryString(b));
     int c=a&b;
     System.out.println("Result AND & "+Integer.toBinaryString(c)+"   =  "+c);
     c=a | b;
     System.out.println("Result OR | "+Integer.toBinaryString(c)+"   =  "+c);
     c=a^b;
     System.out.println("Result XOR ^ "+Integer.toBinaryString(c)+"   =  "+c);
     System.out.println("Complement of "+a+" = "+Integer.toBinaryString(~a));
     System.out.println("Complement of "+b+" = "+Integer.toBinaryString(~b));
      
    }
    
    public static void main(String[] args){
        
     System.out.println(11 % 3);   
     Float f = new Float(12);
     System.out.println(f.MAX_VALUE);
     
     Long l= new Long(3400);
     System.out.println("A long has values of : "+l.MAX_VALUE+"   "+l.MIN_VALUE);
     
     float pi =3.14159f;
     System.out.println("Pi = "+pi+"   or \u0059 ");
     
     short flag = 20; // 0000 0010 0000 0100 - or - 0x0014
     short mask = 4; //  0000 0000 0000 0100
     Short result1 = new Short((short)(flag ^= mask)); // xor
     Short result2 = new Short((short)(flag & mask)); // and
     Short result3 = new Short((short)(flag | mask)); // or
     System.out.println(result1+"   "+result2+"   "+result3);
     System.out.println(Integer.toBinaryString(result1.intValue()));
     
     int x = 10;    // 0000 0000 0000 0000 -low bits another 16 upper ones
     
     Primitives.test(10,2);
    }
    
}
