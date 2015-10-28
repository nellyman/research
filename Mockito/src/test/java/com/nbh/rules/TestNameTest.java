/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.rules;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 *
 * @author  nhardwic
 */
public class TestNameTest {

    @Rule
    public TestName name = new TestName();
    @Test
    public void testA() {
        Assert.assertEquals("testA", this.name.getMethodName());
    }
    @Test
    public void testB() {
        Assert.assertEquals("testB", this.name.getMethodName());
    }
}