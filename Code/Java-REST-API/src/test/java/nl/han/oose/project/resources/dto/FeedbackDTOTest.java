package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FeedbackDTOTest {

    FeedbackDTO sut;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new FeedbackDTO();
    }

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int userId = 1;
        int nodeId = 2;
        String feedback = "This is a sample feedback";

        // Act
        FeedbackDTO feedbackDTO = new FeedbackDTO(userId, nodeId, feedback);

        // Assert
        Assertions.assertEquals(userId, feedbackDTO.getUserId());
        Assertions.assertEquals(nodeId, feedbackDTO.getNodeId());
        Assertions.assertEquals(feedback, feedbackDTO.getFeedback());
    }

    @Test
    void testSetters() {
        // Arrange
        FeedbackDTO feedbackDTO = new FeedbackDTO(1, 2, "Initial feedback");

        // Act
        feedbackDTO.setUserId(3);
        feedbackDTO.setNodeId(4);
        feedbackDTO.setFeedback("Updated feedback");

        // Assert
        Assertions.assertEquals(3, feedbackDTO.getUserId());
        Assertions.assertEquals(4, feedbackDTO.getNodeId());
        Assertions.assertEquals("Updated feedback", feedbackDTO.getFeedback());
    }
}
