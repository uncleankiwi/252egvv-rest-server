package com.kovunov.rest;

import com.kovunov.entity.League;
import com.kovunov.entity.Team;
import com.kovunov.service.LeagueService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/leagues")
public class LeagueResource {

	@EJB
	LeagueService leagueService;

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
	public Response addTeamToLeague(@PathParam("id") long id, Team team) {
		try {
			leagueService.addTeamToLeague(id, team);
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(e.getMessage())
					.build();
		}

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