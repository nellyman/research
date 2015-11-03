/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.research.nested;



/**
 * An anonymous class can access members of the enclosing class. It can also access local variables
 * which are declared final or effectively final (since Java 8).
 *
 * @author nhardwic
 *
 */
public class AnonymousInnerWrapper {

    private String text;

    public DoSomething getSomethingPerhapsBasedOnAnArgument() {
        return new DoSomething() {
            @Override
            public String getMessage() {
                final String msg = AnonymousInnerWrapper.this.text+"!!";
                return "Hello";
            }
        };
    }

    /**
     * @param text the text to set
     * @return the AnonymousInner
     */
    public AnonymousInnerWrapper setText(final String text) {
        this.text = text;
        return this;
    }
}
