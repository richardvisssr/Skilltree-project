package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.FeedbackService;
import nl.han.oose.project.resources.dto.FeedbackDTO;

import java.sql.SQLException;
import java.util.logging.Logger;

@Path("/feedback")
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
            return Response.status(Response.Status.OK).entity(feedbackService.getFeedback(studentId, nodeId)).build();
        } catch (SQLException e) {
            LOGGER.info(ERROR_MESSAGE + e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFeedback(
            FeedbackDTO feedback
    ) {
        try {
            return Response.status(Response.Status.OK).entity(feedbackService.updateFeedback(feedback.getNodeId(), feedback.getUserId(), feedback.getFeedback())).build();
        } catch (SQLException e) {
            LOGGER.info(ERROR_MESSAGE + e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Inject
    public void setFeedbackService(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
}
