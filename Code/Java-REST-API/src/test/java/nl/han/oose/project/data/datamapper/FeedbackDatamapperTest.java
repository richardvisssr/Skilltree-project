package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.FeedbackDTO;
import nl.han.oose.project.resources.dto.FeedbacksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FeedbackDatamapperTest {

    private FeedbackDatamapper sut;
    private ResultSet resultSet;

    @BeforeEach
    void setup() {
        sut = new FeedbackDatamapper();
        resultSet = mock(ResultSet.class);
    }

    @Test
    void testMap() throws SQLException {
        // Arrange
        List<FeedbackDTO> expectedFeedbacks = new ArrayList<>();
        expectedFeedbacks.add(new FeedbackDTO(1, 1, "Feedback 1"));
        expectedFeedbacks.add(new FeedbackDTO(2, 2, "Feedback 2"));
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("StudentID")).thenReturn(1, 2);
        when(resultSet.getInt("NodeID")).thenReturn(1, 2);
        when(resultSet.getString("Feedback")).thenReturn("Feedback 1", "Feedback 2");

        // Act
        FeedbacksDTO actualFeedbacks = sut.map(resultSet);

        // Assert
        assertEquals(expectedFeedbacks.get(0).getUserId(), actualFeedbacks.getFeedbacks().get(0).getUserId());
        assertEquals(expectedFeedbacks.get(1).getUserId(), actualFeedbacks.getFeedbacks().get(1).getUserId());
        assertEquals(expectedFeedbacks.get(0).getNodeId(), actualFeedbacks.getFeedbacks().get(0).getNodeId());
        assertEquals(expectedFeedbacks.get(1).getNodeId(), actualFeedbacks.getFeedbacks().get(1).getNodeId());
        assertEquals(expectedFeedbacks.get(0).getFeedback(), actualFeedbacks.getFeedbacks().get(0).getFeedback());
        assertEquals(expectedFeedbacks.get(1).getFeedback(), actualFeedbacks.getFeedbacks().get(1).getFeedback());
    }

    @Test
    void testMapWithEmptyResultSet() throws SQLException {
        // Arrange
        when(resultSet.next()).thenReturn(false);

        // Act
        FeedbacksDTO actualFeedbacks = sut.map(resultSet);

        // Assert
        assertEquals(0, actualFeedbacks.getFeedbacks().size());
    }

    @Test
    void testMapWithSQLException() throws SQLException {
        // Arrange
        when(resultSet.next()).thenThrow(new SQLException());

        // Act & Assert
        assertThrows(SQLException.class, () -> sut.map(resultSet));
    }
}
