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
public class StockBroker {

    private final static BigDecimal LIMIT = new BigDecimal("0.10");

    private final Market market;

    public StockBroker(final Market market) {
        this.market = market;
    }

    public void perform(final Portfolio portfolio,final Stock stock) {
        final Stock liveStock = this.market.getQuote(stock.getSymbol());
        final BigDecimal avgPrice = portfolio.getAvgPrice(stock);
        final BigDecimal priceGained =
                liveStock.getPrice().subtract(avgPrice);
        final BigDecimal percentGain = priceGained.divide(avgPrice);
        if(percentGain.compareTo(StockBroker.LIMIT) > 0) {
            portfolio.sell(stock, 10);
        }else if(percentGain.compareTo(StockBroker.LIMIT) < 0){
            portfolio.buy(stock);
        }
    }
}

