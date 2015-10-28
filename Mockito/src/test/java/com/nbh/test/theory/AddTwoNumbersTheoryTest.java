/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.test.theory;

import org.junit.Before;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.nbh.theories.AddTwoNumbers;

/**
 *
 * @author  nhardwic
 */
@RunWith(Theories.class)
public class AddTwoNumbersTheoryTest {

    @DataPoints
    public static Integer[] vals = new Integer[] {-1, null, Integer.MAX_VALUE};

    AddTwoNumbers numberAdder;

    @Before
    public void setup() {
        this.numberAdder= new AddTwoNumbers();
    }

    @Theory
    public void sanityTestInputs(final Integer first, final Integer second) {

        final Integer result = this.numberAdder.addNumbers(first, second);
        System.out.println(first+" + "+second+" = "+result);
    }
}
