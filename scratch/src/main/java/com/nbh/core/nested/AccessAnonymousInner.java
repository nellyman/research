/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.core.nested;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;



/**
 * @author nhardwic
 *
 */
public class AccessAnonymousInner {

    Logger logger =LogManager.getRootLogger();

    @Test
    public void expectToGetInnerClass() {
        final AnonymousInnerWrapper inner = new AnonymousInnerWrapper();
        final DoSomething something = inner.getSomethingPerhapsBasedOnAnArgument();
        this.logger.info(something.getMessage());
    }
}
