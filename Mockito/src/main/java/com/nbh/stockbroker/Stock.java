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
public interface Stock {

    BigDecimal getPrice();

    String getSymbol();

    void updatePrice(BigDecimal newPrice);
}
