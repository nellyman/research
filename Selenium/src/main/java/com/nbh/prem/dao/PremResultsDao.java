/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.prem.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.nbh.prem.model.Fixture;
import com.nbh.prem.model.Result;
import com.nbh.prem.model.TeamFactory;



/**
 * @author nhardwic
 *
 */
public class PremResultsDao {

    private final Pattern fixtureDatePattern = Pattern.compile("^\\D+ \\d{2} \\D+ \\d{4}");
    public final String score_Pattern="\\d*[^\\s]"; // outputs 12,-,1
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");


    TeamFactory teamFactory;

    public List<Result> loadResultsData(final int year) {

        this.teamFactory = TeamFactory.getInstance();

        final WebDriver driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(500, 300));
        driver.get("http://www.premierleague.com/content/premierleague/en-gb/matchday/results.html"
                + "?paramClubId=ALL"
                + "&paramComp_8=true"
                + "&paramSeasonId="+year
                + "&view=.dateSeason");

        final List<WebElement> tables = driver.findElements(By.className("contentTable"));

        for (final WebElement table : tables) {
            final String line = table.getText();

            final Matcher m = this.fixtureDatePattern.matcher(line);
            LocalDate fixtureDate= null;

            if (m.find()) {
                final String date = m.group();
                fixtureDate= this.getDateTime(date);
                System.out.println("date '"+date+"'");
            }

            final List<WebElement>times = table.findElements(By.className("time"));

            final List<WebElement> homeTeams = table.findElements(By.className("rHome"));

            final List<WebElement> awayTeams = table.findElements(By.className("rAway"));

            final List<WebElement> scores = table.findElements(By.className("score"));

            for (int i =0; i< times.size(); i++) {
                final String time = times.get(i).getText();

                final int hours = Integer.parseInt(time.substring(0, 1));
                final int minutes = Integer.parseInt(time.substring(3, 4));

                final LocalDateTime fixtureTime = LocalDateTime.of(fixtureDate, LocalTime.of(hours, minutes));

                final String home = homeTeams.get(i).getText();
                final String away = awayTeams.get(i).getText();
                final String scoresStr = scores.get(i).getText();
                final int[] scoresInts = this.getScore(scoresStr);

                final Fixture fixture = new Fixture(this.teamFactory.getTeam(home), this.teamFactory.getTeam(away), fixtureTime);
                final Result r = new Result(fixture, scoresInts[0], scoresInts[1]);
                System.out.println(r.toString());
            }
        }

        return null;
    }

    private LocalDate getDateTime(final String line) {
        try {
            return LocalDate.parse(line, this.formatter);
        }catch (final DateTimeParseException e) {
            // not a date...
            System.out.println(e);
            return null;
        }
    }

    private int[] getScore(final String scoreStr) {
        final Pattern p = Pattern.compile(this.score_Pattern);
        final Matcher m = p.matcher(scoreStr);
        final int[] score = new int[2];
        if (m.find()) {
            System.out.println("score : '"+m.group(0)+"'");
            System.out.println("score : '"+m.group(1)+"'");
            System.out.println("score : '"+m.group(2)+"'");
            score[0] = Integer.parseInt(m.group(0));
            score[1] = Integer.parseInt(m.group(2));
        }
        return score;
    }

}
