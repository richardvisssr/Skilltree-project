package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.UserDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.UsersDTO;

import java.sql.*;
import java.util.List;

public class StudentDAO {
    private UserDatamapper datamapper;
    private static final int STUDENT_ROL_ID = 2;
    private DatabaseProperties databaseProperties;
    private Connection connection;
    private PreparedStatement stmt;

    public UsersDTO getAllStudents() throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            return datamapper.map(getAllStudentsQuery());
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
            stmt.close();
        }
    }

    public UsersDTO getStudentsBySkilltree(int skilltreeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            return datamapper.map(getStudentsBySkilltreeQuery(skilltreeId));
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
            stmt.close();
        }
    }

    public void addStudentsToSkilltree(List<Integer> newStudents, int skilltreeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            for (Integer studentId : newStudents) {
                addStudentToSkilltreeQuery(studentId, skilltreeId);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
            stmt.close();
        }
    }

    public void removeStudentsFromSkilltree(List<Integer> deletedStudents, int skilltreeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            for (Integer studentId : deletedStudents) {
                removeStudentFromSkilltreeQuery(studentId, skilltreeId);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
            stmt.close();
        }
    }

    private void removeStudentFromSkilltreeQuery(Integer studentId, int skilltreeId) throws SQLException {
        try {
            var query = "DELETE FROM userskilltree\n" +
                    "WHERE userID = ? AND skilltreeID = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, studentId);
            stmt.setInt(2, skilltreeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private ResultSet getAllStudentsQuery() throws SQLException {
        try {
            var query = "SELECT ID, Firstname, Lastname, Email, RoleId FROM Users WHERE RoleId = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, STUDENT_ROL_ID);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private ResultSet getStudentsBySkilltreeQuery(int skilltreeId) throws SQLException{
        try {
            var query = "SELECT u.ID, u.Firstname, u.Lastname, u.Email, u.RoleID \n" +
                    "FROM Users u \n" +
                    "JOIN userskilltree us \n" +
                    "ON u.ID = us.userId\n" +
                    "WHERE us.skilltreeId = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, skilltreeId);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


    private void addStudentToSkilltreeQuery(int studentId, int skilltreeId) throws SQLException {
        try {
            var query = "INSERT INTO userskilltree (userId, skilltreeId) VALUES (?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, studentId);
            stmt.setInt(2, skilltreeId);
            stmt.executeUpdate();
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
