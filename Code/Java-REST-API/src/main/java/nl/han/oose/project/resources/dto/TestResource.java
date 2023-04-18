package nl.han.oose.project.resources.dto;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.TestService;

@Path("/test")
public class TestResource {
    private TestService testService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response testResourceMethod() {
        return Response.status(200).entity(testService.testServiceMethod()).build();
    }

    @Inject
    public void setTestService(TestService testService) {
        this.testService = testService;
    }
}
