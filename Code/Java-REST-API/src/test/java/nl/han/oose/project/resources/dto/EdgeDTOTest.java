package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EdgeDTOTest {
    private EdgeDTO sut;

    @BeforeEach
    void setup() {
        sut = new EdgeDTO();
    }

    @Test
    void testConstructor() {
        // Arrange
        var expectedEdgeId = "test";
        var expectedSourceId = "test";
        var expectedTargetId = "test";
        var expectedSkillTreeID = 5;

        // Act
        var result = new EdgeDTO("test", "test", "test", 5);

        // Assert
        Assertions.assertEquals(expectedEdgeId, result.getEdgeId());
        Assertions.assertEquals(expectedSourceId, result.getSourceId());
        Assertions.assertEquals(expectedTargetId, result.getTargetId());
        Assertions.assertEquals(expectedSkillTreeID, result.getSkillTreeID());
    }

    @Test
    void testEdgeId() {
        // Arrange
        String expected = "test";
        sut.setEdgeId(expected);

        // Act
        var result = sut.getEdgeId();

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSourceId() {
        // Arrange
        String expected = "test";
        sut.setSourceId(expected);

        // Act
        var result = sut.getSourceId();

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testTargetId() {
        // Arrange
        String expected = "test";
        sut.setTargetId(expected);

        // Act
        var result = sut.getTargetId();

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSkillTreeID() {
        // Arrange
        int expected = 5;
        sut.setSkillTreeID(expected);

        // Act
        var result = sut.getSkillTreeID();

        // Assert
        Assertions.assertEquals(expected, result);
    }
}
