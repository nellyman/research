/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.stockbroker;

/**
 *
 * @author  nhardwic
 */

public interface Market {


    /**
     * @param symbol
     * @return
     */
    Stock getQuote(String symbol);
}
