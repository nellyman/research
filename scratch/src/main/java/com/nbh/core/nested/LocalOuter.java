/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.core.nested;



/**
 * @author nhardwic
 *
 */
public class LocalOuter {

    public String  getMessage() {
        class Local{
            String getMessage() {
                return "123";
            }
        }

        final Local local = new Local();
        return local.getMessage();
    }
}
