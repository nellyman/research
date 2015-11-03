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
public class AccessInner {

    Logger logger =LogManager.getRootLogger();

    @Test
    public void shouldGetInnerClass() {
        final NonStaticNestedOuter outer = new NonStaticNestedOuter();
        final NonStaticNestedOuter.NonStaticInner inner = outer.new NonStaticInner();
        this.logger.info(inner.getText());
    }

}
