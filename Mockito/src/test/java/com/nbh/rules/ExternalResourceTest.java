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
import org.junit.rules.ExternalResource;
import org.junit.rules.TestName;

/**
 * External Resources can be handled in an external resource rule.
 * This rule provides methods for handling the resource.
 *
 * @author  nhardwic
 */

public class ExternalResourceTest {

    Resource resource;

    public @Rule TestName name = new TestName();

    public @Rule ExternalResource rule = new ExternalResource() {

        @Override
        protected void before() throws Throwable {
            ExternalResourceTest.this.resource = new Resource();
            ExternalResourceTest.this.resource.open();
            System.out.println(ExternalResourceTest.this.name.getMethodName());
        }

        @Override
        protected void after() {
            ExternalResourceTest.this.resource.close();
            System.out.println("\n");
        }
    };

    @Test
    public void someTest() throws Exception {
        System.out.println(this.resource.get());
    }

    @Test
    public void someTest2() throws Exception {
        System.out.println(this.resource.get());
    }
}


class Resource{
    public void open() {
        System.out.println("Opened");
    }
    public void close() {
        System.out.println("Closed");
    }
    public double get() {
        return Math.random();
    }
}


