package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.AccountService;

import java.sql.SQLException;
import java.util.logging.Logger;

@Path("/accounts")
public class AccountResource {

    private static final Logger LOGGER  = Logger.getLogger(NodeResource.class.getName());
    private static final String ERROR_MESSAGE = "Error ";
    private AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts() {
        try {
            return Response.status(Response.Status.OK).entity(accountService.getAllAccounts()).build();
        } catch (SQLException e) {
            LOGGER.info(ERROR_MESSAGE + e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
