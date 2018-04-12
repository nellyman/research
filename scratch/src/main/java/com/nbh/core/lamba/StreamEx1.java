package com.nbh.core.lamba;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by nhardwic on 22/02/2017.
 */
public class StreamEx1 {

    public static void main(String[] args){
        List<String> list= Arrays.asList("eggs", "beans", "sausage");
        list = list.subList(0,2);
        Stream<String> stream = list.stream();
        //list.add("bacon, dont forget bacon!!");
        stream.forEach(System.out::println);
    }
}
