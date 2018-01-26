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
 * @author nhardwic
 */
public class MaterialisedStock implements Stock {

    private final String symbol;
    private final String name;
    private BigDecimal price;

    /**
     * @param price
     * @param symbol
     * @param name
     */
    public MaterialisedStock(final BigDecimal price, final String symbol, final String name) {
        super();
        this.price = price;
        this.symbol = symbol;
        this.name = name;
    }

    /* (non-Javadoc)
     * @see com.nbh.stockbroker.Stock#getPrice()
     */
    public BigDecimal getPrice() {

        return this.price;
    }

    /* (non-Javadoc)
     * @see com.nbh.stockbroker.Stock#getSymbol()
     */
    public String getSymbol() {
        // TODO Auto-generated method stub
        return this.symbol;
    }

    /* (non-Javadoc)
     * @see com.nbh.stockbroker.Stock#updatePrice(java.math.BigDecimal)
     */
    public void updatePrice(final BigDecimal newPrice) {
        this.price = newPrice;
    }

}
