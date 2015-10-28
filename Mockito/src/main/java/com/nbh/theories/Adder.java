/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.theories;

/**
 *
 * @author  nhardwic
 */

public class Adder {
    public Object add(final Number a, final Number b) {
        return a.doubleValue()+b.doubleValue();
    }
    public Object add(final String a, final String b) {
        return a+b;
    }
}
