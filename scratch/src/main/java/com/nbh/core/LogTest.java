package com.nbh.core;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: Jun 10, 2004
 * Time: 9:15:05 AM
 * To change this template use Options | File Templates.
 */
public class LogTest {

    private static Logger logger = Logger.getLogger("com.nbh.test");
    private static Logger fileLogger = Logger.getLogger("com.sun.java");

    public static void main(String args[]) throws Exception{

        FileHandler fh = new FileHandler("log.out");
        // Send logger output to FileHandler.
        fileLogger.addHandler(fh);
        fileLogger.setLevel(Level.ALL);
        fileLogger.log(Level.FINE, "Testing - Level = FINE");




        logger.log(Level.SEVERE, "Testing - Level = SEVERE");
    }


}




