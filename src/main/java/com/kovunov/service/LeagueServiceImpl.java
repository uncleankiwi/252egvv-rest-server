package com.kovunov.service;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LeagueServiceImpl implements LeagueService{

	@PersistenceContext
	private EntityManager em;

	@Override
	public League getById(Long id) {
		return em.find(League.class, id);
	}

	@Override
	public void createLeague() {

	}

	@Override
	public void addTeamToLeague(Team team) {

	}
}
