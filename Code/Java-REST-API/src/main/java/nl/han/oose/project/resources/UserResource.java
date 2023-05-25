package nl.han.oose.project.resources;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.UserService;
import nl.han.oose.project.resources.dto.UserDTO;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.sql.SQLException;

@Path("/users")
public class UserResource {
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserDTO userDTO) throws SQLException {
            userService.createUser(userDTO);
        return Response.status(Response.Status.CREATED).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        try {
            return Response.status(Response.Status.OK).entity(userService.getAllUsers()).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
