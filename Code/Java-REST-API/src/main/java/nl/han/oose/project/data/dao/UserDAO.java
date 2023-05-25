package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.UserDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.UserDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDAO {
    private DatabaseProperties databaseProperties;
    private Connection connection;

    public void createUser(UserDTO userDTO) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        createUserQuery(userDTO);
        connection.close();
    }

    private void createUserQuery(UserDTO userDTO) throws SQLException {
        var query = "INSERT INTO Users(Firstname, Lastname, Email, Password, RoleID)" +
                " VALUES (?, ?, ?, ?, ?)";
        var stmt = connection.prepareStatement(query);
        stmt.setString(1, userDTO.getFirstname());
        stmt.setString(2, userDTO.getLastname());
        stmt.setString(3, userDTO.getEmail());
        stmt.setString(4, userDTO.getPassword());
        stmt.setInt(5, userDTO.getRoleId());
        stmt.executeUpdate();
    }
    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
