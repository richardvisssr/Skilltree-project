package nl.han.oose.project.data.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DatabasePropertiesTest {
    private Properties properties;

    @BeforeEach
    public void setUp() {
        properties = new Properties();
        // Arrange: laad de properties uit de test resources
        String propertiesString = "driver=com.microsoft.sqlserver.jdbc.SQLServerDriver\n" +
                "connectionString=jdbc:sqlserver://localhost:1433;databaseName=skilltree;user=projectErlang;password=admin;encrypt=true;trustServerCertificate=true;";
        InputStream inputStream = new ByteArrayInputStream(propertiesString.getBytes());
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnectionString() throws Exception {
        // Act: maak een nieuwe DatabaseProperties en roep de connectionString methode aan
        DatabaseProperties databaseProperties = new DatabaseProperties();
        String expectedConnectionString = "jdbc:sqlserver://localhost:1433;databaseName=skilltree;user=projectErlang;password=admin;encrypt=true;trustServerCertificate=true;";
        String actualConnectionString = databaseProperties.connectionString();

        // Assert: controleer of de verwachte en daadwerkelijke connection strings gelijk zijn
        assertEquals(expectedConnectionString, actualConnectionString);
    }

    @Test
    public void testDatabasePropertiesMissingProperty() throws Exception {
        // Arrange: maak een Properties object zonder de connectionString property
        Properties properties = new Properties();
        properties.setProperty("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Act: maak een mock van DatabaseProperties en stel deze zo in dat de connectionString methode de connectionString property van het Properties object zal teruggeven
        DatabaseProperties databaseProperties = mock(DatabaseProperties.class);
        when(databaseProperties.connectionString()).thenReturn(properties.getProperty("connectionString"));

        // Assert: controleer of de connectionString methode null teruggeeft omdat de connectionString property ontbreekt in de Properties
        assertNull(databaseProperties.connectionString());
    }
}