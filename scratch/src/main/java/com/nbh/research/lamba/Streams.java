/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.research.lamba;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;



/**
 *
 * http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 *
 * @author nhardwic
 *
 */
public class Streams {

    @Test
    public void expectNothingPrintOutAtNoTerminalOperationIsPresent() {
        Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> {
            System.out.println("filter: " + s);
            return true;
        });
    }

    @Test
    public void expectPrintOutWithTerminalOperation() {
        Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> {
            System.out.println("filter: " + s);
            return true;
        })
        .forEach(s-> System.out.println("forEach "+s));
    }

    /**
     * The operation anyMatch returns true as soon as the predicate applies to the given input element.
     *  This is true for the second element passed "A2". Due to the vertical execution of the stream chain,
     *   map has only to be executed twice in this case. So instead of mapping all elements of the stream, map
     *   will be called as few as possible.
     */
    @Test
    public void expectLessProcessingWithFiltering() {
        Stream.of("d2", "a2", "b1", "b3", "c")
        .map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        })
        .anyMatch(s -> {
            System.out.println("anyMatch: " + s);
            return s.startsWith("A");
        });
    }

    @Test
    public void mapAndFilterExample() {
        Stream.of("d2", "a2", "b1", "b3", "c")
        .map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        })
        .filter(s -> {
            System.out.println("filter: " + s);
            return s.startsWith("A");
        })
        .forEach(s -> System.out.println("forEach: " + s));
    }

    @Test
    public void moveFilterToStartOfProcessingToReduceWork() {
        Stream.of("d2", "a2", "b1", "b3", "c")
        .filter(s -> {
            System.out.println("filter: " + s);
            return s.startsWith("a");
        })
        .map(s -> {
            System.out.println("map: " + s);
            return s.toUpperCase();
        })
        .forEach(s -> System.out.println("forEach: " + s));
    }

    @Test
    public void collectExample() {
        final List<String> items = new ArrayList<String>();
        items.add("one");
        items.add("two");
        items.add("three");

        final List<String> filtered = items.stream()
                .filter( item -> item.startsWith("o") )
                .collect(Collectors.toList());

        Assert.assertEquals(1, filtered.size());
        Assert.assertEquals("one", filtered.get(0));
    }

    @Test
    public void collectToUpperCase() {
        Stream.of("d2", "a2", "b1", "b3", "c")
        .map( item -> item.toUpperCase() )
        .forEach(s -> System.out.println("forEach: " + s));
    }

    //The min() and max() methods return an Optional instance which has a get() method
    @Test
    public void minExample() {
        final String smallestHash = Stream.of("d2", "a2", "b1", "b3", "c")
                .min(Comparator.comparing(item -> item.hashCode()))
                .get();
        System.out.println(smallestHash);
    }

    //returns the number of elements in the stream after filtering has been applied
    @Test
    public void countExample() {
        final long count= Stream.of("d2", "a2", "b1", "b3", "c")
                .filter( item -> item.startsWith("b"))
                .count();
        System.out.println(count);
    }
}
