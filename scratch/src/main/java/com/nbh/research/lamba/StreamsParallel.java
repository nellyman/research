/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.research.lamba;

import java.util.stream.IntStream;

import org.junit.Test;



/**
 * @author nhardwic
 *
 */
public class StreamsParallel {

    @Test
    public void parallelLoopTest() {

        IntStream.iterate(0, i -> ( i + 1 ) % 2)
        .parallel()
        .limit(100000)
        .forEach(System.out::println);
    }
}
