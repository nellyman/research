/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.research.inheritance;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;



/**
 * @author nhardwic
 *
 */
public class InheritExample {

    Logger logger =LogManager.getRootLogger();

    @Test
    public void shouldPerformUpandDownCaseCorrectly() {
        final Car car = new Car();
        // upcasting...
        final Vehicle vehicle = car;

        // downcasting..
        Car car2 =(Car)vehicle;

        //using interface...
        final Vehicle car3 = new Car();
        this.logger.info(car3.getName());
        car2 =(Car)vehicle;
        this.logger.info(car2.getName());
    }

    @Test(expected=ClassCastException.class)
    public void shouldFailUpandDownCaseCorrectly() {
        final Car car = new Car();
        // upcasting...
        final Vehicle vehicle = car;

        // downcasting..
        final Lorry lorry =(Lorry)vehicle;
    }

    @Test
    public void shouldActionSuperClassMethod() {

        final Vehicle lorry = new Lorry();
        this.logger.info("Of course the vehicle object is an actual Lorry- "+lorry.getName());

        final Lorry lorry2 = new Van();
        this.logger.info("The Van is the actual object created- "+lorry2.getName());

    }

}
