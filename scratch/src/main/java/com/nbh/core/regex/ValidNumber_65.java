package com.nbh.core.regex;

import org.junit.Assert;

public class ValidNumber_65 {

    public static void main(String[] args) {

        Assert.assertTrue(isNumber("0"));
        Assert.assertTrue(isNumber(" 0.1"));
        Assert.assertFalse(isNumber("abc"));
        Assert.assertFalse(isNumber("1 a"));
        Assert.assertTrue(isNumber("2e10"));
    }


    public static boolean isNumber(String s){
        return s.matches("\\d");
    }

}
