package com.nbh.test;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: 07-Sep-2004
 * Time: 17:14:08
 * To change this template use Options | File Templates.
 */
public class StringTest {

    private static String FIRST ="code";
    private static String SECOND ="day";
    private static String THIRD ="month";
    private static String LAST ="year";

    // incoming bean is straight from oracle, format code year month day
    public static void main(String[] args){

        String msg = "O17323120040907";
        String code = msg.substring(0,7);
        String year = msg.substring(7,11);
        String month = msg.substring(11,13);
        String day=msg.substring(13);

        System.out.println(code+" "+year+" "+month+" "+day);

        StringBuffer formattedMsg= new StringBuffer();
        formatLoop(FIRST, formattedMsg, code, year, month, day);
        formatLoop(SECOND, formattedMsg, code, year, month, day);
        formatLoop(THIRD, formattedMsg, code, year, month, day);
        formatLoop(LAST, formattedMsg, code, year, month, day);

        //System.out.println(formattedMsg.toString());

    }

        private static void formatLoop(String Position, StringBuffer msg, String code, String year, String month, String day){
            if (Position.equals("code"))
                 msg.append(code);
            if (Position.equals("year"))
                 msg.append(year);
            if (Position.equals("month"))
                 msg.append(month);
            if (Position.equals("day"))
                 msg.append(day);
        }
}
