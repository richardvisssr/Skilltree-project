package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.EdgeDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.EdgeDTO;
import nl.han.oose.project.resources.dto.EdgesDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EdgeDAO {

    private EdgeDatamapper edgeDatamapper;
    private DatabaseProperties databaseProperties;
    private Connection connection;

    public EdgesDTO getAllEdgesFromSkilltree(int skilltreeId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        return edgeDatamapper.map(getAllEdgesFromSkilltreeQuery(skilltreeId));
    }

    private ResultSet getAllEdgesFromSkilltreeQuery(int skilltreeId) throws SQLException {
        var query = "SELECT EdgeId, TargetID, SourceID, SkillTreeID FROM Edges WHERE SkilltreeID = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, skilltreeId);
        return stmt.executeQuery();
    }

    public EdgesDTO createEdge(EdgeDTO edgeDTO, int skilltreeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            createEdgeQuery(edgeDTO, skilltreeId);
            return getAllEdgesFromSkilltree(skilltreeId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connection.close();
        }
    }

    private void createEdgeQuery(EdgeDTO edgeDTO, int skilltreeId) throws SQLException {
        var query = "INSERT INTO Edges(SourceID, TargetID, SkillTreeID, EdgeId) VALUES (?, ?, ?, ?)";
        var stmt = connection.prepareStatement(query);
        stmt.setString(1, edgeDTO.getSourceId());
        stmt.setString(2, edgeDTO.getTargetId());
        stmt.setInt(3, skilltreeId);
        stmt.setString(4, edgeDTO.getEdgeId());
        stmt.executeUpdate();
    }

    public void deleteEdge(String edgeId) throws SQLException {
        try {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        deleteEdgeQuery(edgeId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connection.close();
        }
    }

    private void deleteEdgeQuery(String edgeId) throws SQLException{
        var query = "DELETE FROM Edges WHERE EdgeId = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setString(1, edgeId);
        stmt.executeUpdate();
    }


    @Inject
    public void setEdgeDatamapperDatamapper(EdgeDatamapper edgeDatamapper) {
        this.edgeDatamapper = edgeDatamapper;
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }
}
