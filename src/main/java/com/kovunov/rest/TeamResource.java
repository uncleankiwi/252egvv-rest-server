package com.kovunov.rest;

import com.kovunov.entity.*;
import com.kovunov.service.PlayerService;
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
	@EJB
	private PlayerService playerService;

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
	public Response updateTeamName(TeamUpdateDto dto) {
		if (dto.getId() == null || dto.getId() <= 0) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\n" +
							"\t\"error\": \"Please provide a valid team id\"\n" +
							"}").build();
		}
		Team teamToUpdate = teamService.getById(dto.getId());
		if (teamToUpdate == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"Error\":\"No team with id " + dto.getId() + " exists\"}")
					.build();
		}

		return Response.ok()
				.entity(teamService.updateTeamName(dto, teamToUpdate))
				.build();
	}

	@Path("/updateplayerteam")
	@PUT
	@Consumes({APPLICATION_JSON})
	@Produces({APPLICATION_JSON})
	//asa
	public Response updatePlayerTeam(PlayerTeamUpdateDto updateDto) {
		if (updateDto.getId() == null || updateDto.getId() == 0 || updateDto.getTeam().getId() == null || updateDto.getTeam().getId() ==0) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\n" +
							"\t\"error\": \"Please provide correct id(s)\"\n" +
							"}").build();
		}
		Player playerToUpdate = playerService.getById(updateDto.getId());
		Team dbTeam =  teamService.getById(updateDto.getTeam().getId());
		if (dbTeam == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\"Error\":\"No team with id " + updateDto.getTeam().getId() + " exists\"}")
					.build();
		}
		if (playerToUpdate == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("{\n" +
							"\t\"error\": \"No such player\"\n" +
							"}").build();
		}
		return Response.ok().entity(teamService.addPlayerToTeam(updateDto, playerToUpdate)).build();

	}



}
