package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FeedbacksDTOTest {

    @Test
    void testFeedbacksDTO() {
        // Arrange
        FeedbacksDTO sut = new FeedbacksDTO();

        // Act
        List<FeedbackDTO> feedbacks = sut.getFeedbacks();

        // Assert
        Assertions.assertNotNull(feedbacks);
        Assertions.assertEquals(0, feedbacks.size());
    }

    @Test
    void testFeedbacksDTOWithList() {
        // Arrange
        FeedbackDTO feedbackDTO = new FeedbackDTO(1, 2, "Feedback 1");
        List<FeedbackDTO> feedbacks = new ArrayList<>();
        feedbacks.add(feedbackDTO);

        // Act
        FeedbacksDTO sut = new FeedbacksDTO(feedbacks);

        // Assert
        Assertions.assertEquals(feedbacks, sut.getFeedbacks());
    }

    @Test
    void testSetFeedbacks() {
        // Arrange
        FeedbacksDTO sut = new FeedbacksDTO();
        FeedbackDTO feedbackDTO = new FeedbackDTO(1, 2, "Feedback 1");
        List<FeedbackDTO> feedbacks = new ArrayList<>();
        feedbacks.add(feedbackDTO);

        // Act
        sut.setFeedbacks(feedbacks);

        // Assert
        Assertions.assertEquals(feedbacks, sut.getFeedbacks());
    }
}
