package nl.han.oose.project.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.resources.dto.EdgeDTO;
import nl.han.oose.project.business.services.EdgeService;

import java.sql.SQLException;

@Path("/edges")
public class EdgeResource {

    private EdgeService edgeService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Path("/skilltrees/{skilltreeId}")
    public Response createEdge(
            EdgeDTO edgeDTO,
            @PathParam("skilltreeId") int skilltreeId
    ) {
        try {
            return Response.status(Response.Status.OK).entity(edgeService.createEdge(edgeDTO, skilltreeId)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/skilltrees/{skilltreeId}")
    public Response getAllEdges(
            @PathParam("skilltreeId") int skilltreeId
    ) {
        try {
            return Response.status(Response.Status.OK).entity(edgeService.getAllEdges(skilltreeId)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Inject
    public void setEdgeService(EdgeService edgeService) {
        this.edgeService = edgeService;
    }
}
