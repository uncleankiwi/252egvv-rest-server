package com.kovunov.rest;

import com.kovunov.entity.Player;
import com.kovunov.entity.PlayerUpdateDto;
import com.kovunov.service.PlayerService;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/players")
public class PlayerResource {

    @EJB
    private PlayerService playerService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service is working").build();
    }

    @DELETE
    @Path("{id}")
    @Produces(TEXT_PLAIN)
    public Response deletePlayer(@PathParam("id") long id) {
        playerService.removeFromList(playerService.getById(id));
        return Response.ok().entity("player deleted").build();
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
        playerService.addToList(player);
        return Response.status(Response.Status.CREATED).entity(player).build();
    }
}
