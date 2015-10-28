/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

/**
 *
 */
package com.nbh.theories;

import java.util.ArrayList;
import java.util.List;

import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

/**
 *
 * @author  nhardwic
 */

public class NumberSupplier extends ParameterSupplier {

    public NumberSupplier() {

    }

    @Override
    public List<PotentialAssignment>    getValueSources(final ParameterSignature sig) {

        final List<PotentialAssignment> list = new ArrayList<PotentialAssignment>();
        list.add(PotentialAssignment.forValue("long", 2L));
        list.add(PotentialAssignment.forValue("float", 5.00f));
        list.add(PotentialAssignment.forValue("double", 89d));
        list.add(PotentialAssignment.forValue("null", null));
        return list;
    }
}