package com.nbh.core;

import java.util.HashSet;
import java.util.Set;

public class UniqueCharsStream {

    public static void main(String[] args) {

        String words = "alfa bravo charlie delta";

        char[] chars = words.toCharArray();

        Set<Character> charSet = new HashSet<>(chars.length);

        for (Character ch : chars){
            charSet.add(ch);
        }
        System.out.println("There are "+chars.length+" total letters");
        System.out.println("There are "+charSet.size()+" unique letters");
    }
}
