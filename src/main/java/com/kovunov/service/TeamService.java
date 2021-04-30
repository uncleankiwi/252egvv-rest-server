package com.kovunov.service;

import com.kovunov.entity.Player;
import com.kovunov.entity.PlayerTeamUpdateDto;
import com.kovunov.entity.Team;

import java.util.List;

public interface TeamService {
	void createTeam(Team team);
	Player addPlayerToTeam(PlayerTeamUpdateDto dto, Player playerToUpdate);
	void updateTeamName(String name);
	Team getById(Long id);
	List<Team> getTeamList();
}
