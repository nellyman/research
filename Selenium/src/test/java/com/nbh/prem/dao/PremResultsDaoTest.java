/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.prem.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nbh.prem.model.Result;




public class PremResultsDaoTest {

    private PremResultsDao dao;

    @Before
    public void before() {
        this.dao = new PremResultsDao();
    }

    @Test
    public void testLoadResults() {
        final List<Result> results = this.dao.loadResultsData(2014);
        Assert.assertNotNull(results);
    }

}
