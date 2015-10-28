/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.research.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author  nhardwic
 */
public class PremTableLoader {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final WebDriver driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(500, 300));
        driver.get("http://www.premierleague.com/en-gb/matchday/league-table.html");

        final List<WebElement> tables = driver.findElements(By.className("club-row"));


        for (final WebElement table : tables) {
            final String rawRow = table.getText();
            System.out.println(rawRow);

            //http://www.regexr.com/
            final String[] name =rawRow.split( "[(\\d)]+/g");

            final String[] parts = rawRow.split(" ");
            System.out.println("current posn "+parts[0]);
            System.out.println("last posn "+parts[1]);
            System.out.println("name "+name[0]);
        }
    }

}
