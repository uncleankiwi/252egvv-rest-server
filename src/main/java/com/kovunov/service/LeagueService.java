package com.kovunov.service;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;

import java.util.List;

public interface LeagueService {
	League getById(Long id);
	void createLeague(League league);
	void addTeamToLeague(Long leagueId, Team team);
	List<League> getLeagueList();
}
