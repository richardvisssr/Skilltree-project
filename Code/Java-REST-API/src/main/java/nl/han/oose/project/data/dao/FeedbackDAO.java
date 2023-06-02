package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.FeedbackDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.FeedbacksDTO;

import java.sql.*;

public class FeedbackDAO {

    private DatabaseProperties databaseProperties;
    private FeedbackDatamapper datamapper;
    private Connection connection;
    private PreparedStatement stmt;

    public FeedbacksDTO getFeedback(int studentId, int nodeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            return datamapper.map(getFeedbackQuery(studentId, nodeId));
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
            stmt.close();
        }
    }

    private ResultSet getFeedbackQuery(int studentId, int nodeId) throws SQLException {
        try {
            var query = "SELECT * FROM Feedback WHERE StudentID = ? AND NodeID = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, studentId);
            stmt.setInt(2, nodeId);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public FeedbacksDTO updateFeedback(int nodeId, int userId, String feedback) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            deleteFeedbackQuery(nodeId, userId);
            addFeedbackQuery(nodeId, userId, feedback);
            return getFeedback(userId, nodeId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connection.close();
            stmt.close();
        }
    }

    private void deleteFeedbackQuery(int nodeId, int userId) throws SQLException {
        try {
            var query = "DELETE FROM Feedback WHERE NodeID = ? AND StudentID = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, nodeId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void addFeedbackQuery(int nodeId, int userId, String feedback) throws SQLException {
        try {
            var query = "INSERT INTO Feedback (NodeID, StudentID, Feedback) VALUES (?, ?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, nodeId);
            stmt.setInt(2, userId);
            stmt.setString(3, feedback);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Inject
    public void setDatamapper(FeedbackDatamapper datamapper) {
        this.datamapper = datamapper;
    }


}
