/*
 * MonthNames.java
 *
 * Created on 15 July 2003, 09:26
 */

package com.nbh.core.date;

import java.text.*;
import java.util.*;
import javax.swing.*;

public class MonthNames {
    public static void main(String args[]) {
        Locale locale;
        if (args.length == 1) {
            locale = new Locale(args[0]);
        } else if (args.length == 2) {
            locale = new Locale(args[0], args[1]);
        } else {
            locale = Locale.getDefault();
        }
        DateFormatSymbols symbols = new DateFormatSymbols(locale);
        String months[] = symbols.getMonths();
        
        StringBuffer message =new StringBuffer();
        for (int i=0; i < months.length; i++) {
            message.append(months[i]+"\n");
        }
        JOptionPane.showMessageDialog(null,message,("months in "+locale.getDisplayLanguage()),JOptionPane.INFORMATION_MESSAGE);
       // JOptionPane.

    }
}
