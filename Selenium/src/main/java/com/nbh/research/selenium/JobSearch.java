/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.research.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Looking at the Selenium tutorial on udemy. Job search
 * @author  nhardwic
 */
public class JobSearch {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(500, 300));
        driver.get("https://www.indeed.com/");
        driver.findElement(By.id("what")).clear();
        driver.findElement(By.id("what")).sendKeys("java developer");

        driver.findElement(By.id("where")).clear();
        driver.findElement(By.id("where")).sendKeys("london");

        driver.findElement(By.id("fj")).click();

        System.out.println(driver.findElement(By.id("searchCount")).getText());
    }

}
