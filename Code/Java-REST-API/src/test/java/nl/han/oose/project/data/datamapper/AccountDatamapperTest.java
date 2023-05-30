package nl.han.oose.project.data.datamapper;

import static org.mockito.Mockito.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import nl.han.oose.project.resources.dto.AccountDTO;
import nl.han.oose.project.resources.dto.AccountsDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDatamapperTest {

    private AccountDatamapper sut;
    private ResultSet resultSet;

    @BeforeEach
    void setup() {
        sut = new AccountDatamapper();
        resultSet = mock(ResultSet.class);
    }

    @Test
    void mapResultSetToAccountsDTO() throws SQLException {
        // Arrange
        List<AccountDTO> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(new AccountDTO(1, "John@gmail.com", 1));
        expectedAccounts.add(new AccountDTO(2, "Jane@gmail.com", 2));
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("ID")).thenReturn(1, 2);
        when(resultSet.getString("Email")).thenReturn("John@gmail.com", "Jane@gmail.com");
        when(resultSet.getInt("RoleID")).thenReturn(1, 2);

        // Act
        AccountsDTO actualAccounts = sut.map(resultSet);

        // Assert
        Assertions.assertEquals(expectedAccounts.get(0).getId(), actualAccounts.getAccounts().get(0).getId());
        Assertions.assertEquals(expectedAccounts.get(1).getId(), actualAccounts.getAccounts().get(1).getId());
        Assertions.assertEquals(expectedAccounts.get(0).getEmail(), actualAccounts.getAccounts().get(0).getEmail());
        Assertions.assertEquals(expectedAccounts.get(1).getEmail(), actualAccounts.getAccounts().get(1).getEmail());
        Assertions.assertEquals(expectedAccounts.get(0).getRoleId(), actualAccounts.getAccounts().get(0).getRoleId());
        Assertions.assertEquals(expectedAccounts.get(1).getRoleId(), actualAccounts.getAccounts().get(1).getRoleId());
    }

    @Test
    void mapResultSetToAccountsDTOWithSQLException() throws SQLException {
        // Arrange
        when(resultSet.next()).thenThrow(new SQLException());

        // Act & Assert
        assertThrows(SQLException.class, () -> sut.map(resultSet));
    }

    @Test
    void mapResultSet2ToAccountsDTO() throws SQLException{
        // Arrange
        List<AccountDTO> expectedAccounts = null;

        // Act
        AccountsDTO actualAccounts = sut.map(resultSet, resultSet);

        // Assert
        Assertions.assertEquals(expectedAccounts, actualAccounts);
    }

}
