
package com.nbh.research.selenium;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Project1TwitterCounter {


    public static void main(final String[] args) {

        final WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        PrintWriter writer=null;
        try {
            writer = new PrintWriter("top1000.txt");
        } catch (final FileNotFoundException ex) {
            System.out.println("File not found.");
        }

        for(int i =0; i<2; i++){
            driver.get("http://twittercounter.com/pages/100/"+(i*100));
            final List<WebElement> uname =driver.findElements(By.className("uname"));
            for(final WebElement e : uname){
                writer.println(e.getAttribute("href").replace("http://twittercounter.com", "https://twitter.com"));
            }
        }
        writer.close();

    }

}
