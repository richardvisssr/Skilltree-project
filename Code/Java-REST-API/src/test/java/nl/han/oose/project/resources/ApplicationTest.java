package nl.han.oose.project.resources;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    public void testGetApplicationPath() {
        // Arrange
        ApplicationResource sut = new ApplicationResource();
        String expectedPath = "/";

        // Act
        String actualPath = sut.getApplicationPath();

        // Assert
        assertEquals(expectedPath, actualPath, "Application path should be /");
    }
}
