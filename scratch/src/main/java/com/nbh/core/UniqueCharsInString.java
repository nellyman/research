package com.nbh.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class UniqueCharsInString {

    public static void main(String[] args) {
        String chars = "abcdefghhaadfeg";

        Set<Character> charSet = new HashSet<>();
        for (char c: chars.toCharArray()){
            charSet.add(c);
        }

        System.out.println("There are :"+charSet.size()+" unique chars");

        // which char appears most...

        HashMap<Character, Integer> frequency = new HashMap<Character, Integer>(chars.length());
        for (char c: chars.toCharArray()){
            Integer count = frequency.get(c);
            if (count==null){
                frequency.put(c, 1);
            }else{
                frequency.put(c, ++count);
            }
        }
        // see the largest one...
        int maxValue=0;
        Character maxChar=null;
        for (char c : frequency.keySet()){
            int value = frequency.get(c);
            if (value>maxValue){
                maxValue=value;
                maxChar = c;
            }
        }

        System.out.println("Most Frequent: "+maxChar+" with "+maxValue+" matches");
    }
}
