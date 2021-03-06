package com.kovunov.rest;

import com.kovunov.entity.Player;
import com.kovunov.entity.PlayerUpdateDto;
import com.kovunov.entity.Team;
import com.kovunov.service.PlayerService;
import com.kovunov.service.TeamService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/players")
public class PlayerResource {

    @EJB
    private PlayerService playerService;

    @EJB
    private TeamService teamService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service is working").build();
    }

    @DELETE
    @Path("{id}")
    @Produces(TEXT_PLAIN)
    public Response deletePlayer(@PathParam("id") Long id) {
        if (id == null || id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Error\":\"" + id + " is not valid player id\"}")
                    .build();
        }
        Player player = playerService.getById(id);
        if (player == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Error\":\"No player with id " + id + " exists\"}")
                    .build();
        }

        playerService.removeFromList(player);
        return Response.ok().entity("Player " + id + " deleted").build();
    }


    @GET
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response getAllPlayersByTeam(Team team) {
        //no valid team id is indicated
        if (team.getId() == null || team.getId() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Error\":\"Please provide a valid team id\"}")
                    .build();
        }

        //team doesn't exist
        Team dbTeam =  teamService.getById(team.getId());
        if (dbTeam == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Error\":\"No team with id " + team.getId() + " exists\"}")
                    .build();
        }

        return Response.ok()
                .entity(playerService.getPlayerListByTeam(team))
                .build();
    }

    @GET
    @Produces({APPLICATION_JSON})
    public Response getAllPlayers() {
        return Response.ok().entity(playerService.getPlayerList()).build();
    }

    @PUT
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response updatePlayer(PlayerUpdateDto updateDto) {
        if (updateDto.getId() == null || updateDto.getId() == 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"Please provide correct id\"\n" +
                            "}").build();
        }
        Player playerToUpdate = playerService.getById(updateDto.getId());
        if (playerToUpdate == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\n" +
                            "\t\"error\": \"No such player\"\n" +
                            "}").build();
        }
        return Response.ok().entity(playerService.updatePlayer(updateDto, playerToUpdate)).build();
    }

    @POST
    @Consumes({APPLICATION_JSON})
    @Produces({APPLICATION_JSON})
    public Response createPlayer(Player player) {
        try {
            playerService.addToList(player);
        }
        catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"Error\":\"User with username " + player.getUserName() + " already exists\"}")
                    .build();
        }

        return Response.status(Response.Status.CREATED).entity(player).build();
    }
}
