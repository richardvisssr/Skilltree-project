package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.SkilltreeService;
import nl.han.oose.project.resources.dto.SkilltreeDTO;

import java.sql.SQLException;

@Path("/skilltrees")
public class SkilltreeResource {
    private SkilltreeService skilltreeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/docenten/{docentId}")
    public Response getAllSkilltrees(
            @PathParam("docentId") int docentId
    ) {
        try {
            return Response.status(Response.Status.OK).entity(skilltreeService.getAllSkilltrees(docentId)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/docenten/{docentId}")
    public Response createSkilltreeQuery(
            SkilltreeDTO skilltreeDTO,
            @PathParam("docentId") int docentId
    ) {
        try {
            return Response.status(Response.Status.OK).entity(skilltreeService.createSkilltree(skilltreeDTO, docentId)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Inject
    public void setSkilltreeService(SkilltreeService skilltreeService) {
        this.skilltreeService = skilltreeService;
    }
}
