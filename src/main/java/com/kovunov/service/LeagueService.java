package com.kovunov.service;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;

public interface LeagueService {
	League getById(Long id);
	void createLeague();
	void addTeamToLeague(Team team);
}
