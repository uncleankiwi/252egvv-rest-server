package com.kovunov.service;

import com.kovunov.entity.League;
import com.kovunov.entity.Player;
import com.kovunov.entity.Team;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TeamServiceImpl implements TeamService {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private LeagueService leagueService;

	@Override
	public void createTeam(Team team) {
		if (team.getLeague() != null) {
			League league = leagueService.getById(team.getLeague().getId());
			team.setLeague(league);
		}
		em.persist(team);
	}

	@Override
	public List<Team> getTeamList() {
		return em.createNamedQuery("Team.findAll", Team.class)
				.getResultList();
	}

	@Override
	public void addPlayerToTeam(Player player) {

	}

	@Override
	public void updateTeamName(String name) {

	}

	@Override
	public Team getById(Long id) {
		return em.find(Team.class, id);
	}
}
