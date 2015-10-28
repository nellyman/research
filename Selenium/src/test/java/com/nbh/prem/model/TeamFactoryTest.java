/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.prem.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nbh.prem.model.TeamFactory.Team;




public class TeamFactoryTest {

    TeamFactory teamFactory;

    @Before
    public void setup() {
        this.teamFactory = TeamFactory.getInstance();
    }

    @Test
    public void expectToGetTeamFromFactory() {
        final Team liverpool = this.teamFactory.getTeam("LiVerPool");
        Assert.assertNotNull(liverpool);
        Assert.assertEquals("Liverpool", liverpool.getName());

        final Team manutd = this.teamFactory.getTeam("Man Utd");
        Assert.assertNotNull(manutd);

        final Team liverpool1 = this.teamFactory.getTeam("LiverPOOL");
        Assert.assertNotNull(liverpool1);
        Assert.assertEquals(liverpool, liverpool1);
    }
}
