package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.UserDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.UsersDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAO {
    private UserDatamapper datamapper;
    private int studentRolId = 2;
    private DatabaseProperties databaseProperties;
    private Connection connection;

    public UsersDTO getAllStudents() throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var result = datamapper.map(getAllStudentsQuery());
        connection.close();
        return result;
    }

    public UsersDTO getStudentsBySkilltree(int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var result = datamapper.map(getStudentsBySkilltreeQuery(skilltreeId));
        connection.close();
        return result;
    }

    public void addStudentsToSkilltree(List<Integer> newStudents, int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        for (Integer studentId : newStudents) {
            addStudentToSkilltreeQuery(studentId, skilltreeId);
        }
        connection.close();
    }

    public void removeStudentsFromSkilltree(List<Integer> deletedStudents, int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        for (Integer studentId : deletedStudents) {
            removeStudentFromSkilltreeQuery(studentId, skilltreeId);
        }
        connection.close();
    }

    private void removeStudentFromSkilltreeQuery(Integer studentId, int skilltreeId) throws SQLException {
        var query = "DELETE FROM userskilltree\n" +
                "WHERE userID = ? AND skilltreeID = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, studentId);
        stmt.setInt(2, skilltreeId);
        stmt.executeUpdate();
    }

    private ResultSet getAllStudentsQuery() throws SQLException {
        System.out.println("je moeder");
        var query = "SELECT ID, Firstname, Lastname, Email, RoleID FROM Users WHERE RoleID = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, studentRolId);
        System.out.println("dikke");
        var result = stmt.executeQuery();
        System.out.println(result + "askldfj");
        return result;
    }

    private ResultSet getStudentsBySkilltreeQuery(int skilltreeId) throws SQLException {
        System.out.println("dsdslkjbdsvjbsdVkidsjBVsdkjV");
        var query = "SELECT u.ID, u.Firstname, u.Lastname, u.Email, u.RoleID \n" +
                "FROM Users u \n" +
                "JOIN userskilltree us \n" +
                "ON u.ID = us.userId\n" +
                "WHERE us.skilltreeId = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, skilltreeId);
        var result = stmt.executeQuery();
        return result;
    }


    private void addStudentToSkilltreeQuery(int studentId, int skilltreeId) throws SQLException {
        var query = "INSERT INTO userskilltree (userId, skilltreeId) VALUES (?, ?)";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, studentId);
        stmt.setInt(2, skilltreeId);
        stmt.executeUpdate();
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
