/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.core.lamba;

import java.util.function.Consumer;

/**
 * Example of the LOAN pattern...
 * @author  nhardwic
 */
public class Resource {

    private Resource() {
        System.out.println("opening resource");
    }

    public void operate() {
        System.out.println("operating a resource");
    }

    public void dangerousOperation() {
        System.out.println("performing a dangerous operation");
    }

    public void close() {
        System.out.println("closing resource");
    }

    /**
     * Encapsulate the try/finally
     * block in a static method of the Resource class and possibly to make its constructor
     * private in order to oblige the clients of that class to use it only through that method:
     *
     * @param consumer
     */
    public static void withResource(final Consumer<Resource> consumer) {
        // creates a new resource
        final Resource resource = new Resource();
        try {
            // calls out to the consumer to see what they want to do with the resource...
            consumer.accept(resource);
        } finally {
            // finished, so close.
            resource.close();
        }
    }
}
