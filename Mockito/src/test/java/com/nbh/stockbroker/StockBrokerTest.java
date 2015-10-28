/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.stockbroker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 *
 * @author  nhardwic
 */

@RunWith(MockitoJUnitRunner.class)
public class StockBrokerTest {

    @Mock
    Market market;

    @Mock
    Portfolio portfolio;

    StockBroker broker;

    Map<String, List<Stock>> stockMap = new HashMap<String, List<Stock>>();

    @Before
    public void setUp() {
        this.broker = new StockBroker(this.market);
    }

    @Test
    public void sanity() throws Exception {
        Assert.assertNotNull(this.market);
        Assert.assertNotNull(this.portfolio);
    }

    @Test
    public void marketWatcher_Returns_current_stock_status() {
        final Stock uvsityCorp = new MaterialisedStock( new BigDecimal("100.00"), "UV", "Uvsity Corporation");

        Mockito.when(this.market.getQuote(Matchers.anyString())).
        thenReturn(uvsityCorp);

        Assert.assertNotNull(this.market.getQuote("UV"));
        Assert.assertEquals(uvsityCorp, this.market.getQuote("UV"));
    }

    @Test
    public void when_ten_percent_gain_then_the_stock_is_sold() {
        //Portfolio's getAvgPrice is stubbed to return $10.00
        Mockito.when(this.portfolio.getAvgPrice(Matchers.isA(Stock.class))).
        thenReturn(new BigDecimal("10.00"));

        //A stock object is created with current price $11.20
        final Stock aCorp = new MaterialisedStock(new BigDecimal("11.20"), "A", "A Corp");

        //getQuote method is stubbed to return the stock
        Mockito.when(this.market.getQuote(Matchers.anyString())).thenReturn(aCorp);

        //perform method is called, as the stock price increases
        // by 12% the broker should sell the stocks
        this.broker.perform(this.portfolio, aCorp);

        //verifying that the broker sold the stocks
        Mockito.verify(this.portfolio).sell(aCorp,10);
    }

    @Test
    public void verify_no_more_interaction() {
        final Stock noStock = null;
        this.portfolio.getAvgPrice(noStock);
        this.portfolio.sell(null, 0);
        Mockito.verify(this.portfolio).getAvgPrice(Matchers.eq(noStock));
        //this will fail as the sell method was invoked
        Mockito.verifyNoMoreInteractions(this.portfolio);
    }

    /**
     * A Mockito spy object allows us to use real objects instead of mocks by replacing
some of the methods with the stubbed ones. This behavior allows us to test the
legacy code; one cannot mock a class that needs to be tested. Legacy code comes with
methods that cannot be tested, but other methods use them; so, these methods need
to be stubbed to work with the other methods. A spy object can stub the nontestable
methods so that other methods can be tested easily.
     * @throws Exception
     */
    @Test
    public void spying() throws Exception {
        final Stock realStock = new MaterialisedStock(BigDecimal.ONE, "A", "Company A");
        final Stock spyStock = Mockito.spy(realStock);

        //call real method from spy
        Assert.assertEquals("A", spyStock.getSymbol());
        // check that 1 is returned on the get price...
        Assert.assertEquals(BigDecimal.ONE, spyStock.getPrice());

        //Changing value using spy
        spyStock.updatePrice(BigDecimal.ZERO);
        //verify spy has the changed value
        Assert.assertEquals(BigDecimal.ZERO, spyStock.getPrice());

        //Stubbing method getPrice will always return 10
        // THIS IS WHAT SPYING IS ALL ABOUT!!
        Mockito.when(spyStock.getPrice()).thenReturn(BigDecimal.TEN);

        //Changing value using spy
        spyStock.updatePrice(new BigDecimal("7"));

        // check return...
        //Stubbed method value 10.00 is returned NOT 7
        Assert.assertNotEquals(new BigDecimal("7"),  spyStock.getPrice());
        //Stubbed method value 10.00
        Assert.assertEquals(BigDecimal.TEN, spyStock.getPrice());
    }

    /**
     * The following code uses two ArgumentCaptors and verifies whether it uses a specific
stock symbol, A, and not any other value while calling the method:

     * Check that ArgumentCaptor takes a Class type in the forClass method
and then the captor is passed to the verify method to collect the argument
details. The sell method takes two arguments, Stock and Integer. So, two
ArgumentCaptors are created. The stockCaptor object captures the Stock argument
and stockSellCountCaptor captures the stock quantity. Finally, the values are
compared to verify whether the correct values were passed to the sell method.

     * @throws Exception
     */
    @Test
    public void argument_captor() throws Exception {

        // setup mocked methods, when we want avg price, return 10
        Mockito.when(this.portfolio.getAvgPrice(Matchers.isA(Stock.class))).thenReturn(new BigDecimal("10.00"));

        // other mock, when we want a stock return A
        final Stock aCorp = new MaterialisedStock(new BigDecimal(11.20), "A", "A Corp");
        Mockito.when(this.market.getQuote(Matchers.anyString())).thenReturn(aCorp);

        // setup argument captors...
        this.broker.perform(this.portfolio, aCorp);
        final ArgumentCaptor<String> stockIdCaptor =  ArgumentCaptor.forClass(String.class);

        Mockito.verify(this.market).getQuote(stockIdCaptor.capture());
        Assert.assertEquals("A", stockIdCaptor.getValue());

        //Two arguments captured
        final ArgumentCaptor<Stock> stockCaptor = ArgumentCaptor.forClass(Stock.class);
        final ArgumentCaptor<Integer> stockSellCountCaptor =ArgumentCaptor.forClass(Integer.class);

        // make call...
        Mockito.verify(this.portfolio).sell(stockCaptor.capture(),stockSellCountCaptor.capture());

        // make checks...
        Assert.assertEquals("A", stockCaptor.getValue().getSymbol());
        Assert.assertEquals(10, stockSellCountCaptor.getValue().intValue());
    }

    /**
     * The following test sequentially invokes the getAvgPrice, getCurrentValue,
getQuote, and buy methods, but verifies whether the buy() method is invoked
before the getAvgPrice() method. So, the verification order is wrong and hence
the test fails:
     * @throws Exception
     */
    @Test
    public void inorder() throws Exception {

        final Stock aCorp = new MaterialisedStock(new BigDecimal(11.20), "A", "A Corp");
        this.portfolio.getAvgPrice(aCorp);
        this.portfolio.getCurrentValue();
        this.market.getQuote("X");
        this.portfolio.buy(aCorp);

        final InOrder inOrder=Mockito.inOrder(this.portfolio,this.market);
        inOrder.verify(this.portfolio).getAvgPrice(Matchers.isA(Stock.class));
        inOrder.verify(this.portfolio).getCurrentValue();
        inOrder.verify(this.market).getQuote(Matchers.anyString());
        inOrder.verify(this.portfolio).buy(Matchers.isA(Stock.class));
    }

    @Test
    public void answering() throws Exception {
        this.stockMap.clear();
        Mockito.doAnswer(new BuyAnswer()).when(this.portfolio).buy(Matchers.isA(Stock.class));

        Mockito.when(this.portfolio.getCurrentValue()).
        then(new TotalPriceAnswer());

        this.portfolio.buy(new MaterialisedStock(BigDecimal.TEN, "A", "A"));
        this.portfolio.buy(new MaterialisedStock(BigDecimal.ONE, "B", "B"));
        Assert.assertEquals(new BigDecimal("11"),this.portfolio.getCurrentValue());
    }




    /**
     * nonstubbed methods of a mock object return default values such as
null for an object and false for a Boolean. However, Mockito allows us to change the
default settings.
The following are the allowed settings:
• RETURNS_DEFAULTS: This is the default setting. It returns null for object,
false for Boolean, and so on.
• RETURNS_SMART_NULLS: This returns spy of a given type.
• RETURNS_MOCKS: This returns mocks for objects and the default value
for primitives.
• RETURNS_DEEP_STUBS: This returns a deep stub.
• CALLS_REAL_METHODS: This calls a real method.
The following example overrides the default Mockito settings and uses different
return types:
     * @throws Exception
     */
    @Test
    public void changing_default() throws Exception {
        final Stock aCorp = new MaterialisedStock(new BigDecimal(11.20), "A", "A Corp");
        final Portfolio pf = Mockito.mock(Portfolio.class);

        //default null is returned
        Assert.assertNull(pf.getAvgPrice(aCorp));

        final Portfolio pf1 = Mockito.mock(Portfolio.class, Mockito.RETURNS_SMART_NULLS);
        //a smart null is returned
        System.out.println("#1 "+pf1.getAvgPrice(aCorp));
        Assert.assertNotNull(pf1.getAvgPrice(aCorp));

        final Portfolio pf2 = Mockito.mock(Portfolio.class,  Mockito.RETURNS_MOCKS);
        //a mock is returned
        System.out.println("#2 "+pf2.getAvgPrice(aCorp));
        Assert.assertNotNull(pf2.getAvgPrice(aCorp));

        final Portfolio pf3 = Mockito.mock(Portfolio.class, Mockito.RETURNS_DEEP_STUBS);
        //a deep stubbed mock is returned
        System.out.println("#3 "+pf3.getAvgPrice(aCorp));
        Assert.assertNotNull(pf3.getAvgPrice(aCorp));
    }

    /**
     * The following Answer implementation is called when the buy method is invoked.
The invocationOnMock object returns the arguments, and the buy method accepts
only one argument, that is, a Stock object. So, type casted the 0th argument to Stock.
Then, insert Stock to the stockMap object
     * @author nhardwic
     *
     */
    class BuyAnswer implements Answer<Object>{

        public Object answer(final InvocationOnMock invocation) throws  Throwable{

            final Stock newStock = (Stock)invocation.getArguments()[0];
            List<Stock> stocks = StockBrokerTest.this.stockMap.get(newStock.getSymbol());
            if(stocks != null) {
                stocks.add(newStock);
            }else {
                stocks = new ArrayList<Stock>();
                stocks.add(newStock);
                StockBrokerTest.this.stockMap.put(newStock.getSymbol(), stocks);
            }
            return null;
        }
    }


    class TotalPriceAnswer implements Answer<BigDecimal>{

        public BigDecimal answer(final InvocationOnMock invocation) throws Throwable {
            BigDecimal totalPrice = BigDecimal.ZERO;
            for(final String stockId: StockBrokerTest.this.stockMap.keySet()) {
                for(final Stock stock:StockBrokerTest.this.stockMap.get(stockId)) {
                    totalPrice = totalPrice.add(stock.getPrice());
                }
            }
            return totalPrice;
        }
    }
}
