package com.kovunov.service;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;

import java.util.List;

public interface LeagueService {
	League getById(Long id);
	List<League> getLeagueList();
	void addTeamToLeague(Long leagueId, Team team);
}
