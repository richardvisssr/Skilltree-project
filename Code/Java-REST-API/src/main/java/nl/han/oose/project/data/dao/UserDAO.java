package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.UserDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;

import nl.han.oose.project.resources.dto.UsersDTO;
import nl.han.oose.project.resources.dto.UserRegistrationDTO;

import java.sql.*;

public class UserDAO {
    private UserDatamapper datamapper;
    private DatabaseProperties databaseProperties;
    private Connection connection;
    private PreparedStatement stmt;

    public void createUser(UserRegistrationDTO userRegistrationDTO) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            createUserQuery(userRegistrationDTO);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            stmt.close();
            connection.close();
        }
    }

    public UsersDTO getAllUsers() throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            return datamapper.map(getAllUsersQuery());
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            stmt.close();
            connection.close();
        }
    }

    private void createUserQuery(UserRegistrationDTO userRegistrationDTO) throws SQLException {
        try {
            var query = "INSERT INTO Users(Firstname, Lastname, Email, Password, RoleID)" +
                    " VALUES (?, ?, ?, ?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, userRegistrationDTO.getFirstname());
            stmt.setString(2, userRegistrationDTO.getLastname());
            stmt.setString(3, userRegistrationDTO.getEmail());
            stmt.setString(4, userRegistrationDTO.getPassword());
            stmt.setInt(5, userRegistrationDTO.getRoleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private ResultSet getAllUsersQuery() throws SQLException {
        try {
            var query = "SELECT ID, Firstname, Lastname, Email, RoleID\n" +
                    "FROM Users";
            stmt = connection.prepareStatement(query);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
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
