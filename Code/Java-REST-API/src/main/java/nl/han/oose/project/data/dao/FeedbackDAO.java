package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.FeedbackDatamapper;
import nl.han.oose.project.data.datamapper.SkilltreeDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.FeedbackDTO;

import java.sql.*;

public class FeedbackDAO {

    private DatabaseProperties databaseProperties;
    private FeedbackDatamapper datamapper;
    private Connection connection;
    private PreparedStatement stmt;

    public FeedbackDTO getFeedback(int studentId, int nodeId) throws SQLException {
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

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
    @Inject
    public void setDatamapper(FeedbackDatamapper datamapper) {
        this.datamapper = datamapper;
    }

}
