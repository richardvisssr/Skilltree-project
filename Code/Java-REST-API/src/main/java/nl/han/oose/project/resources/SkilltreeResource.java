package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.SkilltreeService;

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
            return Response.status(200).entity(skilltreeService.getAllSkilltrees(docentId)).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Inject
    public void setSkilltreeService(SkilltreeService skilltreeService) {
        this.skilltreeService = skilltreeService;
    }
}
