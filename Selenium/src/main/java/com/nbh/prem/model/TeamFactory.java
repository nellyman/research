/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.prem.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.text.WordUtils;



/**
 * @author nhardwic
 *
 */
public class TeamFactory {

    public class Team{
        private final String name;

        public Team(final String teamName) {
            this.name=teamName;
        }
        public String getName() {
            return this.name;
        }
    }

    private final Set<Team> teams = new HashSet<TeamFactory.Team>();

    static TeamFactory instance=null;

    private TeamFactory() {
        // private default constructor.
    }

    public static TeamFactory getInstance() {
        if (TeamFactory.instance==null) {
            TeamFactory.instance = new TeamFactory();
        }
        return TeamFactory.instance;
    }

    public Team getTeam(final String teamName) {

        final String cleanedTeamName = WordUtils.capitalize(teamName.toLowerCase());

        final Set<Team>matchedTeams = this.teams.stream()
                .filter( team-> team.getName().equals(cleanedTeamName) )
                .collect(Collectors.toSet());
        if (matchedTeams.size()==0) {

            final Team team = new Team(cleanedTeamName);
            this.teams.add(team);
            return team;
        }
        return matchedTeams.iterator().next();
    }


}
