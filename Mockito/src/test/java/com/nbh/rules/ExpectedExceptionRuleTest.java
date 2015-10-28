/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author  nhardwic
 */
public class ExpectedExceptionRuleTest {

    // Sets the Rule up...
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    // no rule set, nothing expected to be thrown.
    @Test
    public void throwsNothing() {
    }

    // sets up a null pointer throw.
    @Test
    public void throwsNullPointerException() {
        this.thrown.expect(NullPointerException.class);
        throw new NullPointerException();
    }

    // checks the error message..
    @Test
    public void throwsIllegalStateExceptionWithMessage() {
        this.thrown.expect(IllegalStateException.class);
        this.thrown.expectMessage("Is this a legal state?");
        throw new IllegalStateException("Is this a legal state?");
    }
}