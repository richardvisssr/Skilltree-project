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
public class StudentDAO {
    private StudentDatamapper datamapper;
    private DatabaseProperties databaseProperties;
    private Connection connection;
    public StudentsDTO getAllStudents() throws SQLException {
        connection = DriverManager.getConnection(databaseProperties.connectionString());
        var result = datamapper.map(getAllStudentsQuery());
        connection.close();
        return result;
    }

    private ResultSet getAllStudentsQuery() throws SQLException {
        var query = "SELECT * FROM Users WHERE RoleId = 2";
        var stmt = connection.prepareStatement(query);
        var result = stmt.executeQuery();
        return result;
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Inject
    public void setDatamapper(StudentDatamapper datamapper) {
        this.datamapper = datamapper;
    }
}
