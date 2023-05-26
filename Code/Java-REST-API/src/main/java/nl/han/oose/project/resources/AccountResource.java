package nl.han.oose.project.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.AccountService;

import java.sql.SQLException;

@Path("/accounts")
public class AccountResource {
    private AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts() {
        try {
            return Response.status(Response.Status.OK).entity(accountService.getAllAccounts()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
