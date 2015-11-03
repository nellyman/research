/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.research.lamba;

import org.junit.Test;

/**
 *
 * @author  nhardwic
 */
public class TestResource {

    @Test
    public void testUseResource() {

        // we are going to run 'operate on the resource'.
        Resource.withResource(resource -> resource.operate());

        // so the resource argument is what is created with the 'withResource' method.
        // it is exposed to be used as an argument
        Resource.withResource(resource -> resource.dangerousOperation());

    }
}
