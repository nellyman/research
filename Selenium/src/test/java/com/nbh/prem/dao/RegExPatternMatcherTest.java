/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.prem.dao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;



/**
 * @author nhardwic
 *
 */
public class RegExPatternMatcherTest {

    public final String Date_Pattern="^\\D+ \\d{2} \\D+ \\d{4}";
    public final String Fixture_Pattern="\\d{2}:\\d{2} \\D+ \\d+ - \\d+ \\D+";

    public final String Fixture_Time="\\d{2}:\\d{2}";
    public final String HomeTeam_Pattern=" \\D+ \\d+";
    public final String score_Pattern="\\d+ - \\d+";
    public final String awayTeam_Pattern=" - \\d \\D+";

    public String input="Saturday 16 August 2014"+
            " 12:45 Manchester United 1 - 2 Swansea City Old Trafford"+
            " 15:00 Stoke City 0 - 1 Aston Villa Britannia Stadium";

    public final String score_Pattern2="\\d*[^\\s]";

    public String scoreInput="12 - 1";

    @Test
    public void testExtractScore2() {
        final Pattern p = Pattern.compile(this.score_Pattern2);
        final Matcher m = p.matcher(this.scoreInput);
        while (m.find()) {
            System.out.println("score : '"+m.group()+"'");
        }
    }


    @Test
    public void testExtractDate() {
        final Pattern p = Pattern.compile(this.Date_Pattern);
        final Matcher m = p.matcher(this.input);
        while (m.find()) {
            System.out.println("Time : '"+m.group()+"'");
        }
    }

    @Test
    public void testExtractFixture() {
        System.out.println(this.extractFixture());
    }

    public String extractFixture() {
        final Pattern p = Pattern.compile(this.Fixture_Pattern);
        final Matcher m = p.matcher(this.input);
        if (m.find()) { // while gets all data, not first match
            return m.group();
        }
        return null;
    }


    @Test
    public void testExtractFixtureTime() {
        final Pattern p = Pattern.compile(this.Fixture_Time);
        final String s = this.extractFixture();
        final Matcher m = p.matcher(s);
        if (m.find()) {
            System.out.println("Time: '"+m.group()+"'");
        }
    }

    @Test
    public void testExtractHomeTeam() {
        final Pattern p = Pattern.compile(this.HomeTeam_Pattern);
        final String s = this.extractFixture();
        final Matcher m = p.matcher(s);
        if (m.find()) {
            System.out.println("Home: '"+m.group()+"'");
        }
    }

    @Test
    public void testExtractScore() {
        final Pattern p = Pattern.compile(this.score_Pattern);
        final String s = this.extractFixture();
        final Matcher m = p.matcher(s);
        if (m.find()) {
            final String raw = m.group();
            System.out.println("score: '"+raw+"'");
        }
    }

    @Test
    public void testExtractAwayTeam() {
        final Pattern p = Pattern.compile(this.awayTeam_Pattern);
        final String s = this.extractFixture();
        final Matcher m = p.matcher(s);
        if (m.find()) {
            System.out.println("Away: '"+m.group()+"'");
        }
    }
}
