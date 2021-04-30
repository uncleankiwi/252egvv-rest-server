package com.kovunov.service;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class LeagueServiceImpl implements LeagueService{

	@EJB
	private TeamService teamService;

	@PersistenceContext
	private EntityManager em;

	@Override
	public League getById(Long id) {
		return em.find(League.class, id);
	}

	@Override
	public void createLeague(League league) {
		em.persist(league);
	}

	public List<League> getLeagueList() {
		return em.createNamedQuery("League.findAll", League.class)
				.getResultList();
	}

	@Override
	public void addTeamToLeague(Long leagueId, Team team) {
		League league = getById(leagueId);
		Team teamToAdd = teamService.getById(team.getId());
		teamToAdd.setLeague(league);
		em.merge(teamToAdd);
	}
}
