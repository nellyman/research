package com.nbh.tutorials.date;

/**
 *
 *
Classes GregorianCalendar and Calendar
 *
Calendar is an abstract base class for converting between a Date object and a set of integer fields such as YEAR, MONTH, DAY, HOUR, and so on. (A Date object represents a specific instant in time with millisecond precision. 
 See Date for information about the Date class.) 

Subclasses of Calendar interpret a Date according to the rules of a specific calendar system. 
 The platform provides one concrete subclass of Calendar: GregorianCalendar. Future subclasses could 
 represent the various types of lunar calendars in use in many parts of the world. 

Because Calendar is an abstract class, a Calendar object is never instantiated. 
 The static methods defined in the Calendar class are accessed using the Calendar class name 
 with the dot operator and the attribute: 

Calendar.DAY_OF_WEEK

The GregorianCalendar class is a concrete subclass of Calendar, and provides the standard calendar. 
 GregorianCalendar implements Gregorian and Julian calendars. Dates are computed by extrapolating 
 he current rules indefinitely far backward and forward in time. 

Values calculated for the WEEK_OF_YEAR field range from 1 to 53. Week 1 for a year is the earliest 
 seven day period starting on getFirstDayOfWeek that contains at least getMinimalDaysInFirstWeek days 
 from that year. Therefore, it depends on the values of getMinimalDaysInFirstWeek, getFirstDayOfWeek, 
 and the day of the week of January 1. Weeks between week 1 of one year and week 1 of the following year 
 are numbered sequentially from 2 to 52 or 53 (as needed). 

Values calculated for the WEEK_OF_MONTH field range from 0 to 6. Week 1 of a month (the days with
 WEEK_OF_MONTH = 1) is the earliest set of at least getMinimalDaysInFirstWeek() contiguous days in that 
 month, ending on the day before getFirstDayOfWeek(). Unlike week 1 of a year, week 1 of a month may be 
 shorter than 7 days, need not start on getFirstDayOfWeek(), and will not include days of the previous 
 month. Days of a month before week 1 have a WEEK_OF_MONTH of 0. 

For example, the following application creates a Gregorian calendar, and prints it 
 to the console, marking today's date with an *: 

 *
 ***/

import java.util.*;

public class CalendarExample
 {
 
  public static void main(String []args)
   {
   //Construct new calendar
   
   GregorianCalendar d = new GregorianCalendar();
   
   int today = d.get(Calendar.DAY_OF_MONTH);
   int month = d.get(Calendar.MONTH);
   
   d.set(Calendar.DAY_OF_MONTH, 1);
   
   int weekday = d.get(Calendar.DAY_OF_WEEK);
   
   System.out.println("Sun Mon Tue Wed Thu Fri Sat");
   
   for (int i = Calendar.SUNDAY; i < weekday; i++)
       System.out.print("    ");
       
   do
   {
     int day = d.get(Calendar.DAY_OF_MONTH);
     
     if (day < 10) System.out.print(" ");
     System.out.print(day);
     
     if(day == today)
       System.out.print("* ");
     else 
       System.out.print("  ");
       
     if (weekday == Calendar.SATURDAY)
       System.out.println();
           
     d.add(Calendar.DAY_OF_MONTH, 1);
     weekday = d.get(Calendar.DAY_OF_WEEK);
     
     }
     
     while (d.get(Calendar.MONTH) == month);
     
     if (weekday != Calendar.SUNDAY)
       System.out.println();
       
   }
}

