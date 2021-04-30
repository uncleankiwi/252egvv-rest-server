package com.kovunov.rest;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;
import com.kovunov.service.LeagueService;
import com.kovunov.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/leagues")
public class LeagueResource {

	@EJB
	LeagueService leagueService;

	@EJB
	TeamService teamService;

	@GET
	@Path("/ping")
	public Response ping() {
		return Response.ok().entity("League service is working").build();
	}

	@POST
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response createLeague(League league) {
		leagueService.createLeague(league);
		return Response.status(Response.Status.CREATED).entity(league).build();
	}

	@PUT
	@Path("{id}")
	@Consumes({APPLICATION_JSON})
	@Produces(TEXT_PLAIN)
	public Response addTeamToLeague(@PathParam("id") Long id, Team team) {
		if (id == null || id <= 0) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\n" +
							"\t\"error\": \"Please provide a valid league id\"\n" +
							"}").build();
		}
		if (team.getId() == null || team.getId() <= 0) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\n" +
							"\t\"error\": \"Please provide a valid team id\"\n" +
							"}").build();
		}

		Team dbTeam = teamService.getById(team.getId());
		League league =  leagueService.getById(id);
		if (league == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"Error\":\"No league with id " + id + " exists\"}")
					.build();
		}
		if (dbTeam == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"Error\":\"No team with id " + team.getId() + " exists\"}")
					.build();
		}
		leagueService.addTeamToLeague(id, team);
		return Response.ok()
				.entity("Added team id " + team.getId() + " to league id " + id)
				.build();
	}

	@GET
	@Produces({APPLICATION_JSON})
	public Response getAllLeagues() {
		return Response.ok()
				.entity(leagueService.getLeagueList())
				.build();
	}
}