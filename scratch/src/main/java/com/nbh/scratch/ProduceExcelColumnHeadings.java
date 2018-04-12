/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.scratch;

import java.util.stream.IntStream;

import org.junit.Test;



/**
 * @author nhardwic
 *
 */
public class ProduceExcelColumnHeadings {


    @Test
    public void expectColumnHeadingsToBeProduced() {
        for (int i=0;i<100; i++) {
            System.out.println(ProduceExcelColumnHeadings.getString(i));
        }
    }

    @Test
    public void exceptToUseStreamsApi() {

        IntStream.iterate(0, i -> (i + 1 ))
        .limit(100)
        .forEach(s-> System.out.println(ProduceExcelColumnHeadings.getString(s)));
    }

    private static String getString(int n) {
        final char[] buf = new char[(int) Math.floor(Math.log(25 * (n + 1)) / Math.log(26))];
        for (int i = buf.length - 1; i >= 0; i--) {
            n--;
            buf[i] = (char) ('A' + n % 26);
            n /= 26;
        }
        return new String(buf);
    }


    @Test
    public void expectSomeAlphabetTesting() {
        int n=16;
        final char[] buf = new char[(int) Math.floor(Math.log(25 * (n + 1)) / Math.log(26))];
        for (int i = buf.length - 1; i >= 0; i--) {
            n--;
            buf[i] = (char) ('A' + n % 26);
            n /= 26;
        }
        System.out.println(buf);
    }
}
