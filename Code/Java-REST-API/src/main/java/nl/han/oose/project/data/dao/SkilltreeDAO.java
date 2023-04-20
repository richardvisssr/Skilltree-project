package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.Datamapper;
import nl.han.oose.project.data.datamapper.SkilltreeDatamapper;
import nl.han.oose.project.data.datamapper.TestDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;
import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private ResultSet getAllSkilltreesQuery(int docentId) throws SQLException {
        var query = "SELECT * FROM Skilltrees WHERE UserID = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, docentId);
        var result = stmt.executeQuery();
        return result;
    }

    public void updateSkilltreeQuery(double id, String title, String description) throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var query = "UPDATE Skilltrees SET Title = ?, Description = ? WHERE ID = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setString(1, title);
        stmt.setString(2, description);
        stmt.setDouble(3, id);
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
