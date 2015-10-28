/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.theories;

import org.junit.experimental.theories.ParametersSuppliedBy;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;


/**
 *
 * @author  nhardwic
 */

@RunWith(Theories.class)
public class AdderTest {

    @Theory
    public void adds_numbers(
            @ParametersSuppliedBy(NumberSupplier.class) final Number num1,
            @ParametersSuppliedBy(NumberSupplier.class) final Number num2){

        System.out.println(num1 + " and " + num2);
    }
}




