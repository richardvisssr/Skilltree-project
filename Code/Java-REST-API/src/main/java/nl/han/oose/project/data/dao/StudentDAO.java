package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.StudentDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.StudentDTO;
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

    public void addStudentsToSkilltree(List<Integer> studentsIds, int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        for (Integer studentId : studentsIds) {
            if (!isStudentInSkilltree(studentId, skilltreeId)) {
                addStudentToSkilltreeQuery(studentId, skilltreeId);
            }
        }
        connection.close();
    }

    private ResultSet getAllStudentsQuery() throws SQLException {
        var query = "SELECT Firstname, Lastname FROM Users WHERE RoleId = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, studentRolId);
        var result = stmt.executeQuery();
        return result;
    }

    private ResultSet getStudentsBySkilltreeQuery(int skilltreeId) throws SQLException {
        var query = "SELECT u.Firstname, u.Lastname " +
                "FROM Users u JOIN userskilltree us ON u.Id = us.userId" +
                " WHERE us.skilltreeId = ?";
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

    private boolean isStudentInSkilltree(int studentId, int skilltreeId) throws SQLException {
        var query = "SELECT COUNT(*) FROM userskilltree WHERE userId = ? AND skilltreeId = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, studentId);
        stmt.setInt(2, skilltreeId);
        var resultSet = stmt.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);
        return count > 0;
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
