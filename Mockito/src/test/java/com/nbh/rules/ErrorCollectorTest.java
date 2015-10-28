/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.rules;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/**
 * An Error collector can hold multiple failing test details.
 * @author  nhardwic
 */
public class ErrorCollectorTest {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    /**
     * Even though one test is failing, we get three entries in the error log.
     */
    @Test
    public void fails_after_execution() {
        this.collector.checkThat("a", CoreMatchers.equalTo("b"));
        this.collector.checkThat(1, CoreMatchers.equalTo(2));
        this.collector.checkThat("ae", CoreMatchers.equalTo("g"));
    }
}
