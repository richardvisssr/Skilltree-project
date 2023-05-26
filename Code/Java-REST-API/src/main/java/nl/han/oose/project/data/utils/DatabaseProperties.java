package nl.han.oose.project.data.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseProperties {
    private static final Logger LOGGER  = Logger.getLogger(DatabaseProperties.class.getName());
    private Properties properties;
    public DatabaseProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Can not access property file database.properties", e);
        }
    }

    public String connectionString()
    {
        return properties.getProperty("connectionString");
    }
}
