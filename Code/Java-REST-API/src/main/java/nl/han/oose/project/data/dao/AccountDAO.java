package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.AccountDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.AccountsDTO;

import java.sql.*;

public class AccountDAO {
    private AccountDatamapper datamapper;
    private DatabaseProperties databaseProperties;
    private Connection connection;
    private PreparedStatement stmt;

    public AccountsDTO getAllAccounts() throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            return datamapper.map(getAllAccountsQuery());
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            stmt.close();
            connection.close();
        }
    }

    private ResultSet getAllAccountsQuery() throws SQLException {
        try {
            var query = "SELECT ID, Email, RoleID FROM Users";
            stmt = connection.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
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
