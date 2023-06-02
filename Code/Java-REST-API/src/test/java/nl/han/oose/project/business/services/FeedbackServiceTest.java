package nl.han.oose.project.business.services;

import nl.han.oose.project.data.dao.FeedbackDAO;
import nl.han.oose.project.resources.dto.FeedbacksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FeedbackServiceTest {

    private FeedbackService sut;
    private FeedbackDAO feedbackDAO;

    private static final int STUDENT_ID = 1;
    private static final int NODE_ID = 2;
    private static final String FEEDBACK = "Sample feedback";

    @BeforeEach
    void setup() {
        feedbackDAO = mock(FeedbackDAO.class);
        sut = new FeedbackService();
        sut.setFeedbackDAO(feedbackDAO);
    }

    @Test
    void testGetFeedback() throws SQLException {
        // Arrange
        FeedbacksDTO expectedFeedbacks = new FeedbacksDTO();
        when(feedbackDAO.getFeedback(STUDENT_ID, NODE_ID)).thenReturn(expectedFeedbacks);

        // Act
        FeedbacksDTO actualFeedbacks = sut.getFeedback(STUDENT_ID, NODE_ID);

        // Assert
        assertEquals(expectedFeedbacks, actualFeedbacks);
        verify(feedbackDAO, times(1)).getFeedback(STUDENT_ID, NODE_ID);
    }

    @Test
    void testUpdateFeedback() throws SQLException {
        // Arrange
        FeedbacksDTO expectedFeedbacks = new FeedbacksDTO();
        when(feedbackDAO.updateFeedback(NODE_ID, STUDENT_ID, FEEDBACK)).thenReturn(expectedFeedbacks);

        // Act
        FeedbacksDTO actualFeedbacks = sut.updateFeedback(NODE_ID, STUDENT_ID, FEEDBACK);

        // Assert
        assertEquals(expectedFeedbacks, actualFeedbacks);
        verify(feedbackDAO, times(1)).updateFeedback(NODE_ID, STUDENT_ID, FEEDBACK);
    }
}
