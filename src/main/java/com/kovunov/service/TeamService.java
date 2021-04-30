package com.kovunov.service;

import com.kovunov.entity.Player;
import com.kovunov.entity.PlayerTeamUpdateDto;
import com.kovunov.entity.Team;
import com.kovunov.entity.TeamUpdateDto;

import java.util.List;

public interface TeamService {
	void createTeam(Team team);
	Player addPlayerToTeam(PlayerTeamUpdateDto dto, Player playerToUpdate);
	Team updateTeamName(TeamUpdateDto dto, Team teamToUpdate);
	Team getById(Long id);
	List<Team> getTeamList();
}
