package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NodeDTOTest {
    private NodeDTO sut;

    @BeforeEach
    void setup() {
        sut = new NodeDTO();
    }
    @Test
    void testID() {
        // Arrange
        int expected = 5;
        sut.setID(expected);

        // Act
        var result = sut.getID();

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSkill() {
        // Arrange
        String expected = "test";
        sut.setSkill(expected);

        // Act
        var result = sut.getSkill();

        // Assert
        Assertions.assertEquals(expected, result);
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
    void testPositionX() {
        // Arrange
        int expected = 5;
        sut.setPositionX(expected);

        // Act
        var result = sut.getPositionX();

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testPositionY() {
        // Arrange
        int expected = 5;
        sut.setPositionY(expected);

        // Act
        var result = sut.getPositionY();

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testSkilltreeId() {
        // Arrange
        int expected = 5;
        sut.setSkilltreeId(expected);

        // Act
        var result = sut.getSkilltreeId();

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testLearningOutcome() {
        // Arrange
        String expected = "test";
        sut.setLearningOutcome(expected);

        // Act
        var result = sut.getLearningOutcome();

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testAssessmentCriteria() {
        // Arrange
        List<AssessmentCriteriaDTO> expected = new ArrayList<>();
        expected.add(new AssessmentCriteriaDTO(
                "hello",
                "world"
        ));
        sut.setAssessmentCriteria(expected);

        // Act
        var result = sut.getAssessmentCriteria();

        // Assert
        Assertions.assertEquals(expected, result);
    }


}
