package com.kovunov.service;

import com.kovunov.entity.Player;
import com.kovunov.entity.TeamUpdateDto;
import com.kovunov.entity.Team;

import java.util.List;

public interface TeamService {
	void createTeam(Team team);
	void addPlayerToTeam(Player player);
	Team updateTeamName(TeamUpdateDto dto, Team teamToUpdate);
	Team getById(Long id);
	List<Team> getTeamList();
}
