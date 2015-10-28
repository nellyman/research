/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.rules;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.junit.runners.model.Statement;

/**
 *
 * @author  nhardwic
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestWatcherTest {

    private static String dog = "";

    @Rule
    public TestWatcher watchman = new TestWatcher() {

        @Override
        public Statement apply(final Statement base,final Description description) {
            return super.apply(base, description);
        }
        @Override
        protected void succeeded(final Description description) {
            TestWatcherTest.dog += description.getDisplayName() + " " + "success!\n";
        }
        @Override
        protected void failed(final Throwable e, final Description description) {
            TestWatcherTest.dog += description.getDisplayName() + " " +
                    e.getClass().getSimpleName() + "\n";
        }
        @Override
        protected void starting(final Description description) {
            super.starting(description);
        }
        @Override
        protected void finished(final Description description) {
            super.finished(description);
        }
    };
    @Test
    public void red_test() {
        Assert.fail();
    }
    @Test
    public void green() {
    }

    @AfterClass
    public static void afterClass() {
        System.out.println(TestWatcherTest.dog);
    }
}
