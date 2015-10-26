package com.nbh.sql;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: May 13, 2004
 * Time: 11:40:57 AM
 * To change this template use Options | File Templates.
 */
public class SQLDates {

        private static DateFormat DATEFORMAT= new SimpleDateFormat("yyyyMMdd");

    public static void main(String[] args){

        Date d1 = new Date(2004,11,01);
        java.util.Date d2 = new java.util.Date(2004,11,01);
        Calendar cal = Calendar.getInstance();
        cal.set(2004,11,2);
        java.util.Date d3 = cal.getTime();
        System.out.println(DATEFORMAT.format(d1)+"   "+DATEFORMAT.format(d2)+"   "+DATEFORMAT.format(d3));
    }
}
