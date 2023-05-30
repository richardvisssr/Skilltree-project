package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.SkilltreeDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;

import java.sql.*;

public class SkilltreeDAO {
    private SkilltreeDatamapper datamapper;
    private DatabaseProperties databaseProperties;
    private Connection connection;
    private PreparedStatement stmt;

    public SkilltreesDTO getAllSkilltrees(int gebruikerId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            return datamapper.map(getAllSkilltreesQuery(gebruikerId));
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connection.close();
            stmt.close();
        }
    }

    public SkilltreesDTO createSkilltree(SkilltreeDTO skilltreeDTO, int gebruikerId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            createSkilltreeQuery(skilltreeDTO, gebruikerId);
            return getAllSkilltrees(gebruikerId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connection.close();
            stmt.close();
        }
    }

    public void deleteSkilltree(int skilltreeId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            deleteSkilltreeQuery(skilltreeId);
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connection.close();
            stmt.close();
        }
    }

    private void deleteSkilltreeQuery(int skilltreeId) throws SQLException {
        try {
            var deleteNodeQuery = "DELETE FROM SkillTrees WHERE ID = ?";
            stmt = connection.prepareStatement(deleteNodeQuery);
            stmt.setInt(1, skilltreeId);
            stmt.execute();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public SkilltreesDTO updateSkilltree(SkilltreeDTO skilltreeDTO, int gebruikerId) throws SQLException {
        try {
            connection = DriverManager.getConnection(databaseProperties.connectionString());
            updateSkilltreeQuery(skilltreeDTO, gebruikerId);
            return getAllSkilltrees(gebruikerId);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connection.close();
            stmt.close();
        }
    }

    private ResultSet getAllSkilltreesQuery(int gebruikerId) throws SQLException {
        try {
            var query = "SELECT * FROM Skilltrees WHERE UserID = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, gebruikerId);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void createSkilltreeQuery(SkilltreeDTO skilltreeDTO, int gebruikerId) throws SQLException {
        try {
            var query = "INSERT INTO Skilltrees(title, description, UserID) VALUES (?, ?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, skilltreeDTO.getTitle());
            stmt.setString(2, skilltreeDTO.getDescription());
            stmt.setInt(3, gebruikerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    private void updateSkilltreeQuery(SkilltreeDTO skilltreeDTO, int gebruikerId) throws SQLException {
        try {
            var query = "UPDATE SkillTrees\n" +
                    "SET Title = ?, Description= ? \n" +
                    "WHERE ID = ? AND UserID = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, skilltreeDTO.getTitle());
            stmt.setString(2, skilltreeDTO.getDescription());
            stmt.setInt(3, skilltreeDTO.getId());
            stmt.setInt(4, gebruikerId);
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
    public void setDatamapper(SkilltreeDatamapper datamapper) {
        this.datamapper = datamapper;
    }
}
