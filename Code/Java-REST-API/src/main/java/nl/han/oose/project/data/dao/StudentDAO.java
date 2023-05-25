package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.StudentDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.StudentsDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAO {
    private StudentDatamapper datamapper;
    private int studentRolId = 2;
    private DatabaseProperties databaseProperties;
    private Connection connection;

    public StudentsDTO getAllStudents() throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var result = datamapper.map(getAllStudentsQuery());
        connection.close();
        return result;
    }

    public StudentsDTO getStudentsBySkilltree(int skilltreeId) throws SQLException {
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
        var query = "SELECT ID, Firstname, Lastname FROM Users WHERE RoleId = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, studentRolId);
        return stmt.executeQuery();
    }

    private ResultSet getStudentsBySkilltreeQuery(int skilltreeId) throws SQLException {
        var query = "SELECT u.ID, u.Firstname, u.Lastname \n" +
                "FROM Users u \n" +
                "JOIN userskilltree us \n" +
                "ON u.ID = us.userId\n" +
                "WHERE us.skilltreeId = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, skilltreeId);
        return stmt.executeQuery();
    }


    private void addStudentToSkilltreeQuery(int studentId, int skilltreeId) throws SQLException {
        var query = "INSERT INTO userskilltree (userId, skilltreeId) VALUES (?, ?)";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, studentId);
        stmt.setInt(2, skilltreeId);
        stmt.executeUpdate();
    }

    @Inject
    public void setDatamapper(StudentDatamapper datamapper) {
        this.datamapper = datamapper;
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
