/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.prem.model;

import java.io.Serializable;




/**
 * @author nhardwic
 *
 */
public class Result implements Serializable{

    private static final long serialVersionUID = 8802077000548991449L;

    private final Fixture fixture;
    private final int homeScore;
    private final int awayScore;

    public Result(final Fixture fixture, final int hScore, final int aScore) {
        this.fixture=fixture;
        this.homeScore = hScore;
        this.awayScore = aScore;
    }

    /**
     * @return the fixture
     */
    public Fixture getFixture() {
        return this.fixture;
    }

    /**
     * @return the homeScore
     */
    public int getHomeScore() {
        return this.homeScore;
    }

    /**
     * @return the awayScore
     */
    public int getAwayScore() {
        return this.awayScore;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Result [fixture=" + this.fixture + ", homeScore=" + this.homeScore + ", awayScore=" + this.awayScore + "]";
    }


}
