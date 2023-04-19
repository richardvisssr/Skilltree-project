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
    private SkilltreeDatamapper datamapper = new SkilltreeDatamapper();
    private DatabaseProperties databaseProperties;

    public SkilltreesDTO getAllSkilltrees(int docentId) throws SQLException {
        //TODO echte data ophalen uit database
        var result = datamapper.map(getAllSkilltreesQuery(docentId));
        return result;
    }

    private ResultSet getAllSkilltreesQuery(int docentId) throws SQLException {
        Connection connection = DriverManager.getConnection(databaseProperties.connectionString());

        var query = "SELECT * FROM Skilltrees WHERE UserID = ?";
        var stmt = connection.prepareStatement(query);
        stmt.setInt(1, docentId);
        return stmt.executeQuery();
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Inject
    public void setTestDatamapper(SkilltreeDatamapper datamapper) {
        this.datamapper = datamapper;
    }
}
