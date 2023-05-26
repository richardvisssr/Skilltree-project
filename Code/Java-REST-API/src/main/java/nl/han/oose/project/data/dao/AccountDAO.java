package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.AccountDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.AccountsDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    private AccountDatamapper datamapper;
    private DatabaseProperties databaseProperties;
    private Connection connection;

    public AccountsDTO getAllAccounts() throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var result = datamapper.map(getAllAccountsQuery());
        connection.close();
        return result;
    }

    private ResultSet getAllAccountsQuery() throws SQLException {
        var query = "SELECT ID, Firstname, Lastname, RoleID FROM Users";
        var stmt = connection.prepareStatement(query);
        return stmt.executeQuery();
    }

    @Inject
    public void setDatamapper(AccountDatamapper datamapper) {
        this.datamapper = datamapper;
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
