/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.research.access;

import com.nbh.research.access.one.Engine;




/**
 * Class Access Modifiers

http://tutorials.jenkov.com/java/access-modifiers.html

It is important to keep in mind that the Java access modifier assigned to a Java class takes precedence over any access modifiers assigned to fields,
constructors and methods of that class. If the class is marked with the default access modifier, then no other class outside the same Java package
can access that class, including its constructors, fields and methods. It doesn't help that you declare these fields public, or even public static.

The Java access modifiers private and protected cannot be assigned to a class. Only to constructors, methods and fields inside classes. Classes can
only have the default (package) and public access modifier assigned to them.
 *
 * @author nhardwic
 *
 */
public class SmallEngine extends Engine {

    public void getSize() {
        // super.getSize() not visible as declared as package visible, but this not in same package!
        // even though extends the class.
    }

    public void getTemp() {
        // temp is package protected method.
        super.getTemperature();
    }
}
