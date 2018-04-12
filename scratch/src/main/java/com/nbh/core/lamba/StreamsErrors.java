/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.core.lamba;

import java.util.stream.IntStream;

import org.junit.Test;



/**
 * @author nhardwic
 *
 */
public class StreamsErrors {

    //you can consume streams only once!!
    @Test(expected=IllegalStateException.class)
    public void expectErrorAsStreamsCannotBeReused() {

        final IntStream stream = IntStream.of(1, 2);
        stream.forEach(System.out::println);

        // That was fun! Let's do it again!
        stream.forEach(System.out::println);
    }

    @Test
    public void expectLimitToBeHonoured() {
        // not having the limit results in infinite iteration...
        IntStream.iterate(0, i -> i + 1)
        .limit(10)
        .forEach(System.out::println);
    }


}
