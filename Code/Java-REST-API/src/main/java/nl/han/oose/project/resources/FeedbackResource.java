package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.FeedbackService;
import nl.han.oose.project.business.services.NodeService;
import nl.han.oose.project.resources.dto.FeedbackDTO;

import java.sql.SQLException;
import java.util.logging.Logger;
@Path("feedback")
public class FeedbackResource {

    private static final Logger LOGGER = Logger.getLogger(NodeResource.class.getName());
    private static final String ERROR_MESSAGE = "Error ";
    private FeedbackService feedbackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/nodes/{nodeId}/students/{studentId}")
    public Response getFeedback(
            @PathParam("nodeId") int nodeId, @PathParam("studentId") int studentId
            ) {
        try {
            return Response.status(Response.Status.OK).entity(feedbackService.getFeedback(nodeId, studentId)).build();
        } catch (SQLException e) {
            LOGGER.info(ERROR_MESSAGE + e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
