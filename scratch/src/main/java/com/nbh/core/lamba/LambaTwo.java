/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.core.lamba;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 *
 * @author  nhardwic
 */

public class LambaTwo {


    /*public boolean isEven(final int number) {
        return number % 2 == 0;
    }*/

    public int doubleIt(final int number) {
        return number * 2;
    }

    public boolean isGreaterThan5(final int number) {
        return number > 5;
    }

    @Test
    public void test() {

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        System.out.println(
                numbers.stream()
                .filter( n -> n % 2 == 0)
                .map(n -> n*2)
                .filter(n ->n>5)
                .findFirst()
                );
    }

}
