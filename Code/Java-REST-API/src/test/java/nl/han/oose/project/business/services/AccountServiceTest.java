package nl.han.oose.project.business.services;

import nl.han.oose.project.data.dao.AccountDAO;
import nl.han.oose.project.resources.dto.AccountDTO;
import nl.han.oose.project.resources.dto.AccountsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class AccountServiceTest {
    private AccountService sut;

    private AccountDAO accountDAO;

    @BeforeEach
    void setup() {
        sut = new AccountService();
        accountDAO = mock(AccountDAO.class);

        sut.setAccountDAO(accountDAO);
    }

    @Test
    void testGetAllAccounts() {
        try {
            // Arrange
            var accountDTO = new AccountDTO(1, "test", 1);
            var expected = new AccountsDTO();
            expected.setAccounts(List.of(accountDTO));
            when(accountDAO.getAllAccounts()).thenReturn(expected);

            // Act
            var result = sut.getAllAccounts();

            // Assert
            org.junit.jupiter.api.Assertions.assertEquals(expected.getAccounts(), result.getAccounts());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
