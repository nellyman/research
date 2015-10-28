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

/**
 * @author nhardwic
 *
 */
public class TwitterCounter {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final WebDriver driver = new FirefoxDriver();

        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(500, 300));

        for (int i=0;i<2;i++) {
            final String url = "http://twittercounter.com/pages/100/"+(i*100);
            System.out.println(url);
            driver.get(url);
            /* final List<WebElement> uname =driver.findElements(By.className("uname"));
            for(final WebElement e : uname){
                System.out.println(e.getAttribute("href").replace("http://twittercounter.com", "https://twitter.com"));
            }*/
            final List<WebElement> clearfix =driver.findElements(By.className("clearfix"));
            StringBuilder line = new StringBuilder();
            for(final WebElement element : clearfix){
                // some clearfix are not what we want!!
                if (element.getAttribute("data-pos")==null) {
                    continue;
                }
                final WebElement name = element.findElement(By.className("name"));
                line.append("name: ");
                line.append(name.getText());

                final WebElement uname = element.findElement(By.className("uname"));
                line.append(" link: ");
                line.append(uname.getAttribute("href").replace("http://twittercounter.com", "https://twitter.com"));

                final WebElement followers = element.findElement(By.className("num-followers"));
                line.append(" followers: ");
                line.append(followers.getText());

                final List<WebElement> stats = element.findElements(By.className("num-following"));
                line.append(" following: ");
                line.append(stats.get(0).getText());


                line.append(" tweets: ");
                line.append(stats.get(1).getText());

                System.out.println(line.toString());
                line = new StringBuilder();
            }

        }
        System.out.println("done");
    }

}
