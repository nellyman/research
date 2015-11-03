/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.research.nested;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;




/**
 * @author nhardwic
 *
 */
public class AccessStaticNested {

    Logger logger =LogManager.getRootLogger();

    @Test
    public void getAccessToNestedStaticClass() {
        this.logger.info(new StaticNestedOuter.StaticNested().sayHello());
    }
}
