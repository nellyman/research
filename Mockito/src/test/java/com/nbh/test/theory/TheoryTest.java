/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.test.theory;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 *
 * @author  nhardwic
 */

@RunWith(Theories.class)
public class TheoryTest {

    @DataPoint
    public static String name ="Jack";

    @DataPoint
    public static String mike ="Mike";

    @DataPoints
    public static char[] chars = new char[] {'A', 'B', 'C'};

    @Theory
    public void sanity() {
        System.out.println("Hello");
    }

    @Theory
    public void sanity(final String aName) {
        System.out.println("1. Sanity check "+aName);
    }

    /**
     * Note 2 x2=4 combinations inserted into this test!
     * @param firstName
     * @param lastName
     */
    @Theory
    public void sanity(final String firstName, final String lastName) {
        System.out.println("2. Sanity check "+firstName+",            "+lastName);
    }

    @Theory
    public void build(final char c, final char d) {
        System.out.println(c+" "+d);
    }

}
