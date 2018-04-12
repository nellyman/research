/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.scratch;

import java.io.BufferedReader;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



/**
 * https://www.reddit.com/r/dailyprogrammer/comments/3hj4o2/20150819_challenge_228_intermediate_use_a_web/
 *
 * @author nhardwic
 *
 */
public class ReadBitcoinInfo {

    @Test
    public void expectToReadBitCoinInformation() throws Exception {
        final WebDriver driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(500, 300));
        driver.get("http://api.bitcoincharts.com/v1/trades.csv"
                + "?symbol=bitfinexUSD");

        final String data  = driver.getPageSource();

        final BufferedReader reader = new BufferedReader(new StringReader(data));
        String line = reader.readLine();
        while (line!=null) {
            final String[] elements = line.split(",");
            try {
                final Long timeMs = Long.parseLong(elements[0]);
                final LocalDateTime time =LocalDateTime.ofEpochSecond(timeMs, 0,ZoneOffset.UTC);
                final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm ss a");
                System.out.println("time: "+format.format(time)+
                        " Bit Coint Price: "+String.format("%.3f", Float.parseFloat(elements[1]))+
                        " Amount "+elements[2]);
            }catch(final NumberFormatException nfe) {
                // carry on ignore...
                System.out.println("Cannot read "+nfe.getMessage());
            }
            line = reader.readLine();
        }
    }
}
