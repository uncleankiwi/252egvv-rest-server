package com.kovunov.rest;

import com.kovunov.entity.Team;
import com.kovunov.entity.TeamUpdateDto;
import com.kovunov.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/teams")
public class TeamResource {
	@EJB
	private TeamService teamService;

	@GET
	@Path("/ping")
	public Response ping() {
		return Response.ok().entity("Service is working").build();
	}

	@POST
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	public Response createTeam(Team team) {
		teamService.createTeam(team);
		return Response.status(Response.Status.CREATED)
				.entity(team)
				.build();
	}

	@GET
	@Produces({APPLICATION_JSON})
	public Response getAllTeams() {
		return Response.ok()
				.entity(teamService.getTeamList())
				.build();
	}

    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
	public Response updateTeamName(TeamUpdateDto updateDto) {
		if (updateDto.getId() == null || updateDto.getId() == 0) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\n" +
							"\t\"error\": \"Please provide correct id\"\n" +
							"}").build();
		}
		Team teamToUpdate = teamService.getById(updateDto.getId());
		if (teamToUpdate == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\n" +
							"\t\"error\": \"No such team\"\n" +
							"}").build();
		}
		return Response.ok().entity(teamService.updateTeamName(updateDto, teamToUpdate)).build();
	}
}
