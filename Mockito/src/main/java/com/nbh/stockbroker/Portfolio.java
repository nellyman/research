/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.stockbroker;

import java.math.BigDecimal;

/**
 *
 * @author  nhardwic
 */
public interface Portfolio {

    BigDecimal getAvgPrice(Stock stock);

    BigDecimal getCurrentValue();

    void sell(Stock stock, int amount);

    void buy(Stock stock);
}
