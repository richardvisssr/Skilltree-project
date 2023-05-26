package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.SkilltreeService;
import nl.han.oose.project.resources.dto.SkilltreeDTO;


import java.sql.SQLException;
import java.util.logging.Logger;

@Path("/skilltrees")
public class SkilltreeResource {
    private static final Logger LOGGER  = Logger.getLogger(SkilltreeResource.class.getName());
    private static final String ERROR_MESSAGE = "Error ";

    private SkilltreeService skilltreeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gebruikers/{gebruikerId}")
    public Response getAllSkilltrees(
            @PathParam("gebruikerId") int gebruikerId
    ) {
        try {
            return Response.status(Response.Status.OK).entity(skilltreeService.getAllSkilltrees(gebruikerId)).build();
        } catch (SQLException e) {
            LOGGER.info("getAllSkilltrees" + ERROR_MESSAGE + e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gebruikers/{gebruikerId}")
    public Response createSkilltree(
            SkilltreeDTO skilltreeDTO,
            @PathParam("gebruikerId") int gebruikerId
    ) {
        try {
            return Response.status(Response.Status.OK).entity(skilltreeService.createSkilltree(skilltreeDTO, gebruikerId)).build();
        } catch (SQLException e) {
            LOGGER.info("createSkilltree" + ERROR_MESSAGE + e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/gebruikers/{gebruikerId}")
    public Response updateSkilltree(
            SkilltreeDTO skilltreeDTO,
            @PathParam("gebruikerId") int gebruikerId
    ) {
        try {
            return Response.status(Response.Status.OK).entity(skilltreeService.updateSkilltree(skilltreeDTO, gebruikerId)).build();
        } catch (SQLException e) {
            LOGGER.info("updateSkilltree" + ERROR_MESSAGE + e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Inject
    public void setSkilltreeService(SkilltreeService skilltreeService) {
        this.skilltreeService = skilltreeService;
    }
}
