/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.core.lamba;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

/**
 * https://dzone.com/articles/why-we-need-lambda-expressions
 *
 * @author  nhardwic
 */

public class LambaOne {

    @Test
    public void testLambaFunctionWithPredicate() {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        this.sumAll(numbers, n -> true);
        this.sumAll(numbers, n -> n % 2 == 0);
        this.sumAll(numbers, n -> n > 3);

    }

    /**
     * Using a Lamba with a functional interface removes the need
     * to use many different types of (in this case) versions of the method.
     * e.g. -see below...
     *
     * @param numbers
     * @param p
     * @return
     */
    public int sumAll(final List<Integer> numbers, final Predicate<Integer> p) {
        int total = 0;
        for (final int number : numbers) {
            if (p.test(number)) {
                total += number;
            }
        }
        return total;
    }

    /**
     * This method adds all numbers..
     * @param numbers
     * @return
     */
    public int sumAll(final List<Integer> numbers) {
        int total = 0;
        for (final int number : numbers) {
            total += number;
        }
        return total;
    }

    /**
     * This method adds even numbers...
     * @param numbers
     * @return
     */
    public int sumAllEven(final List<Integer> numbers) {
        int total = 0;
        for (final int number : numbers) {
            if (number % 2 == 0) {
                total += number;
            }
        }
        return total;
    }

}
