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

import org.junit.Test;



/**
 * @author nhardwic
 *
 */
public class DateFormatterTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");

    public String input="Saturday 16 August 2014";

    @Test
    public void testFormatter() {
        try {
            final LocalDate date = LocalDate.parse(this.input, this.formatter);
            final LocalDateTime time = LocalDateTime.of(date, LocalTime.of(12, 00));
        }catch (final DateTimeParseException e) {
            // not a date...
            System.out.println(e);
        }
    }
}
