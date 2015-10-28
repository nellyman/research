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
import org.junit.rules.TestRule;
import org.junit.rules.Verifier;

/**
 * A Verifier is run after each test verifying state is correct.
 * @author  nhardwic
 */
public class VerifierRuleTest {

    private String errorMsg = null;

    // This verifier tests the state of errorMsg...
    @Rule
    public TestRule rule = new Verifier() {
        @Override
        protected void verify() {
            Assert.assertNull("ErrorMsg should be null after each test execution",VerifierRuleTest.this.errorMsg);
        }
    };

    /**
     * This test does not fail, but the verification fails it's check.
     * @throws Exception
     */
    @Test
    public void testName() throws Exception {
        this.errorMsg = "Giving a value";
    }
}
