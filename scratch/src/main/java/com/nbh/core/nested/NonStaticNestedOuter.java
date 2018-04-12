/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.core.nested;




/**
 * @author nhardwic
 *
 */
public class NonStaticNestedOuter {

    private String text = "Private outer message";

    public class NonStaticInner {

        // an example of Shadowing, inner has same method name as outer field.
        // needs refering to Outer.this.text
        private final String text = "Private inner message";

        public String getText() {
            return NonStaticNestedOuter.this.text+" "+this.text;
        }
    }

    public void setText(final String txt) {
        this.text=txt;
    }

}
