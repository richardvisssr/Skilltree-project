package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.NodeService;
import nl.han.oose.project.resources.dto.NodeRequestDTO;

import java.sql.SQLException;

@Path("/nodes")
public class NodeResource {
    private NodeService nodeService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/skilltrees/{skilltreeId}")
    public Response createNode(
        NodeRequestDTO nodeDTO,
        @PathParam("skilltreeId") int skilltreeId
    ) {
        try {
            return Response.status(Response.Status.OK).entity(nodeService.createNode(nodeDTO, skilltreeId)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/skilltrees/{skilltreeId}")
    public Response getAllNodes(
        @PathParam("skilltreeId") int skilltreeId
    ) {
        try{
            return Response.status(Response.Status.OK).entity(nodeService.getAllNodes(skilltreeId)).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHighestNodeId() {
        try {
            return Response.status(Response.Status.OK).entity(nodeService.getHighestNodeId()).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{nodeId}")
    public Response deleteNode(
            @PathParam("nodeId") int nodeId
    ) {
        try {
            nodeService.deleteNode(nodeId);
            return Response.status(Response.Status.OK).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Inject
    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }
}
