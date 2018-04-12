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
public class AccessLocalInner {

    Logger logger =LogManager.getRootLogger();

    @Test
    public void shouldAccessInnerClassToGetResult() {
        final LocalOuter outer = new LocalOuter();
        this.logger.debug(outer.getMessage());

    }
}
