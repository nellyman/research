/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.prem.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.nbh.prem.model.TeamFactory.Team;



/**
 * @author nhardwic
 *
 */
public class Fixture implements Serializable{

    private static final long serialVersionUID = 7652775040778096229L;

    private final Team home;
    private final Team away;
    private final LocalDateTime fixture;

    public Fixture(final Team home, final Team away, final LocalDateTime fixtureTime) {
        this.home = home;
        this.away = away;
        this.fixture = fixtureTime;
    }

    /**
     * @return the home
     */
    public Team getHome() {
        return this.home;
    }

    /**
     * @return the away
     */
    public Team getAway() {
        return this.away;
    }

    /**
     * @return the fixture
     */
    public LocalDateTime getFixtureDate() {
        return this.fixture;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Fixture [home=" + this.home + ", away=" + this.away + ", time=" + this.fixture + "]";
    }


}
