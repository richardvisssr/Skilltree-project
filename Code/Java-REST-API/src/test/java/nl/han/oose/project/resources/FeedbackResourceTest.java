package nl.han.oose.project.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.FeedbackService;
import nl.han.oose.project.resources.dto.FeedbacksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FeedbackResourceTest {
    private FeedbackResource sut;
    private FeedbackService feedbackService;
    private FeedbacksDTO feedbacksDTO;

    private static final int NODE_ID = 1;
    private static final int STUDENT_ID = 2;

    @BeforeEach
    void setup() {
        sut = new FeedbackResource();
        feedbackService = mock(FeedbackService.class);
        feedbacksDTO = mock(FeedbacksDTO.class);
        sut.setFeedbackService(feedbackService);
    }

    @Test
    void getFeedback() {
        try {
            // Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(feedbackService.getFeedback(STUDENT_ID, NODE_ID)).thenReturn(feedbacksDTO);

            // Act
            var result = sut.getFeedback(NODE_ID, STUDENT_ID);

            // Assert
            assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getFeedbackWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(feedbackService.getFeedback(STUDENT_ID, NODE_ID)).thenThrow(new SQLException());

            // Act
            var result = sut.getFeedback(NODE_ID, STUDENT_ID);

            // Assert
            assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }
}
