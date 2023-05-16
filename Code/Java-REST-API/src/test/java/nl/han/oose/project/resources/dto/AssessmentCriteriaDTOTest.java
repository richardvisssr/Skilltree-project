package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssessmentCriteriaDTOTest {
    private AssessmentCriteriaDTO sut;

    @BeforeEach
    void setup() {
        sut = new AssessmentCriteriaDTO();
    }

    @Test
    void testDescription() {
        // Arrange
        String expected = "test";
        sut.setDescription(expected);

        // Act
        var result = sut.getDescription();

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testCharacter() {
        // Arrange
        String expected = "test";
        sut.setCharacter(expected);

        // Act
        var result = sut.getCharacter();

        // Assert
        Assertions.assertEquals(expected, result);
    }
}
