/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.stockbroker;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author  nhardwic
 */

@RunWith(MockitoJUnitRunner.class)
public class StockBrokerBDDTest {

    @Mock
    Market  marketWatcher;

    @Mock
    Portfolio portfolio;

    StockBroker broker;

    @Before
    public void setUp() {
        this.broker = new StockBroker(this.marketWatcher);
    }
    @Test
    public void  should_sell_a_stock_when_price_increases_by_ten_percent(){

        final Stock aCorp = new MaterialisedStock(new BigDecimal(11.20), "FB", "FaceBook");

        //Given a customer previously bought 10 'FB' stocks at
        //$10.00/per share
        BDDMockito.given(this.portfolio.getAvgPrice(Matchers.isA(Stock.class))).willReturn(new BigDecimal("10.00"));
        BDDMockito.given(this.marketWatcher.getQuote(Matchers.eq("FB"))).willReturn(aCorp);

        //when the 'FB' stock price becomes $11.00
        this.broker.perform(this.portfolio, aCorp);
        //then the 'FB' stocks are sold
        Mockito.verify(this.portfolio).sell(aCorp,10);
    }
}
