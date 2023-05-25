package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.UserDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.UserDTO;
import nl.han.oose.project.resources.dto.UsersDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private UserDatamapper datamapper;
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
        stmt.setString(4, "passpass");
        stmt.setInt(5, userDTO.getRoleId());
        stmt.executeUpdate();
    }


    public UsersDTO getAllUsers() throws SQLException {
        System.out.println("3");
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        System.out.println("4");
        var result = datamapper.map(getAllUsersQuery());
        System.out.println("5");
        connection.close();
        return result;
    }

    private ResultSet getAllUsersQuery() throws SQLException {
        System.out.println("6");
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
