package com.nbh.core;

public class StringCompareTest {

    public static void main(String[] args)
    {
        String s1="Buggy";
        String s2="Bread";

        String s3="BuggyBread";
        String s4="BuggyBread";

        String s5 = new String("LetsDebug");
        String s6 = new String("LetsDebug");

        String s7 = s1+s2;
        String s8 = s5+s6;


        System.out.println("1 "+ (s3.equals(s7))); // Output 1
        System.out.println("2 "+ (s3==s4)); // Output 2
        System.out.println("3 "+ (s5==s6)); // Output 3
        System.out.println("4 "+ (s3==s7)); // Output 4
        System.out.println("5 "+("LetsDebug"==s8)); // Output 5
        System.out.println("6 "+ (s3.hashCode()==s4.hashCode())); // Output 6
        System.out.println("7 "+ (s5.hashCode()==s6.hashCode())); // Output 7

        s7 = s5;

        System.out.println("8 "+ (s7.equals("BuggyBread"))); // Output 8
        System.out.println("9 "+ (s7.equals("LetsDebug"))); // Output 9
        System.out.println("10 "+ (s7 == s5));  // Output 10
    }

}
