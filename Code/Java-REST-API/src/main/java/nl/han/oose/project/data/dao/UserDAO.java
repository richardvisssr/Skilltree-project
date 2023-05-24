package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.UserDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.UsersDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private UserDatamapper datamapper;
    private DatabaseProperties databaseProperties;
    private Connection connection;

    public UsersDTO getAllUsers() throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var result = datamapper.map(getAllUsersQuery());
        connection.close();
        return result;
    }

    private ResultSet getAllUsersQuery() throws SQLException {
        var query = "SELECT ID, Firstname, Lastname, Email, RoleID\n" +
                "FROM Users";
        var stmt = connection.prepareStatement(query);
        return stmt.executeQuery();
    }

    @Inject
    public void setDatamapper(UserDatamapper datamapper) {
        this.datamapper = datamapper;
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
