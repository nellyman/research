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
 * Standings-
 * http://www.premierleague.com/en-gb/matchday/league-table.html?season=2014-2015&timelineView=played&matchNo=29&tableView=CURRENT_STANDINGS
 *
 *
 * @author  nhardwic
 */
public class PremResultsLoader {


    public static void main(final String[] args) {
        final WebDriver driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(500, 300));
        driver.get("http://www.premierleague.com/content/premierleague/en-gb/matchday/results.html"
                + "?paramClubId=ALL"
                + "&paramComp_8=true"
                + "&paramSeasonId=2013"
                + "&view=.dateSeason");

        final List<WebElement> tables = driver.findElements(By.className("contentTable"));

        for (final WebElement table : tables) {
            System.out.println(table.getText());
        }
    }
}
