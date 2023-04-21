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
            throw new RuntimeException(e);
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response updateSkilltreeQuery(
            SkilltreeDTO skilltreeDTO
    ) {
        try {
            skilltreeService.updateSkilltree(skilltreeDTO);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Inject
    public void setSkilltreeService(SkilltreeService skilltreeService) {
        this.skilltreeService = skilltreeService;
    }
}
