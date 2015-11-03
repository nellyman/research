/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.research.nested;



/**
 *
 * An Example of a Static Nested Classes
 *
 *In Java a static nested class is essentially a normal class that has just been nested inside another class.
 *Being static, a static nested class can only access instance variables of the enclosing class via a reference
 *to an instance of the enclosing class
 *
 * In effect, a static nested class is behaviorally a top-level class that has been nested in another top-level class for packaging convenience.
 *
 * @author nhardwic
 *
 */
public class StaticNestedOuter {

    final static int value=1;
    static int value2=2;
    private static int value4=4;

    public static class StaticNested{

        private final int value3=3;

        public String sayHello() {
            // can access static variables of wrapping class.
            // For access to this classes values, can use 'this' ?!
            // no access to value4 as private
            //Outer.value4;
            return "hello "+StaticNestedOuter.value+StaticNestedOuter.value2+this.value3;

        }
    }



}
