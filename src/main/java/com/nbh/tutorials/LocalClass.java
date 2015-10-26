package com.nbh.tutorials;

/**
 * The f method for the object of local class B is called after the createB method completes.
 * The f method accesses the local variable i after i's context (stack frame) has been 
 * destroyed. In this situation, the local variable must be declared final,
 * that is, bound, such that its value can be preserved for later use by the f method. 
 **/


    interface A {
        int f();
    }
    
    public class LocalClass {
        static A createB() {
             final int i = 100;         // The point is this needs to be final.
    
            class B implements A {  //an innner class !
                public int f() {
                    return i;
                }
            }
    
            return new B();     // create the inner class and return the value
        }
    
        public static void main(String[] args) {
            A aref = createB();
    
            System.out.println(aref.f());
        }
    }

