package nl.han.oose.project.data.dao;

import jakarta.inject.Inject;
import nl.han.oose.project.data.datamapper.TestDatamapper;
import nl.han.oose.project.data.utils.DatabaseProperties;

public class TestDAO {
    private TestDatamapper testDatamapper;
    private DatabaseProperties databaseProperties;

    public String testDAOMethod() {
        var queryResult = testDAOString();
        var result = testDatamapper.map(queryResult);
        return result;
    }

    //Dit wordt normaal meestal een ResultSet
    private String testDAOString() {
        //database query
        return "test";
    }

    @Inject
    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    @Inject
    public void setTestDatamapper(TestDatamapper testDatamapper) {
        this.testDatamapper = testDatamapper;
    }
}
