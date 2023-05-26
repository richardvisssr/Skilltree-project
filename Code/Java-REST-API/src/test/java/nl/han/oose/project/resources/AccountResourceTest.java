package nl.han.oose.project.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.AccountService;
import nl.han.oose.project.resources.dto.AccountsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.*;
public class AccountResourceTest {

    private AccountResource sut;
    private AccountsDTO accountsDTO;
    private AccountService accountService;
    @BeforeEach
    void setup() {
        sut = new AccountResource();
        accountsDTO = mock(AccountsDTO.class);
        accountService = mock(AccountService.class);

        sut.setAccountService(accountService);
    }

    @Test
    void getAllAccounts() {
        try {
            //Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(accountService.getAllAccounts()).thenReturn(accountsDTO);

            //Act
            var result = sut.getAllAccounts();

            //Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllAccountsWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(accountService.getAllAccounts()).thenThrow(new SQLException());

            // Act
            var result = sut.getAllAccounts();

            // Arrange
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
