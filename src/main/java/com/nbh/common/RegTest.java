package com.nbh.common;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        if (console== null) {
            System.err.println("No console.");
            System.exit(1);
        }
        while (true) {

        	System.out.println("%nEnter your regex: ");
            Pattern pattern = Pattern.compile(console.readLine());

            System.out.println("Enter input string to search: ");
            Matcher matcher = pattern.matcher(console.readLine());

            boolean found = false;
            while (matcher.find()) {
            	System.out.println("I found the text "+matcher.group()+" starting at " +
                   "index "+matcher.start()+" and ending at index "+matcher.end()+".%n");
                found = true;
            }
            if(!found){
            	System.out.println("No match found.%n");
            }
        }
    }
}