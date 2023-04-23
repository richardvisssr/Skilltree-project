package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.SkilltreeDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.SkilltreeResource;
import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkilltreeDAO {
    private SkilltreeDatamapper datamapper;
    private DatabaseProperties databaseProperties;
    private Connection connection;

    public SkilltreesDTO getAllSkilltrees(int docentId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var result = datamapper.map(getAllSkilltreesQuery(docentId));
        connection.close();
        return result;
    }

    public SkilltreesDTO createSkilltrees(SkilltreeDTO skilltreeDTO, int docentId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        createSkilltreeQuery(skilltreeDTO, docentId);
        connection.close();
        return getAllSkilltrees(docentId);
    }

    private ResultSet getAllSkilltreesQuery(int docentId) throws SQLException {
        var query = "SELECT * FROM Skilltrees WHERE UserID = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, docentId);
        var result = stmt.executeQuery();
        return result;
    }

    private void createSkilltreeQuery(SkilltreeDTO skilltreeDTO, int docentId) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var query = "INSERT INTO Skilltrees(title, description, UserID) VALUES (?, ?, ?)";
        var stmt = connection.prepareStatement(query);
        stmt.setString(1, skilltreeDTO.getTitle());
        stmt.setString(2, skilltreeDTO.getDescription());
        stmt.setInt(3, docentId);
        stmt.executeUpdate();
        connection.close();
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
