package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.EdgeDTO;
import nl.han.oose.project.resources.dto.EdgesDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EdgeDatamapperTest {

    private EdgeDatamapper sut;

    private ResultSet resultSet;

    @BeforeEach
    void setup() {
        sut = new EdgeDatamapper();
        resultSet = mock(ResultSet.class);
    }

    @Test
    void testMap() throws SQLException {
        // Arrange
        List<EdgeDTO> expectedEdges = new ArrayList<>();
        expectedEdges.add(new EdgeDTO("1", "1", "2", 1));
        expectedEdges.add(new EdgeDTO("2", "2", "3", 1));
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getString("EdgeId")).thenReturn("1", "2");
        when(resultSet.getString("TargetID")).thenReturn("1", "2");
        when(resultSet.getString("SourceID")).thenReturn("2", "3");
        when(resultSet.getInt("SkillTreeID")).thenReturn(1, 1);

        // Act
        EdgesDTO actualEdges = sut.map(resultSet);

        // Assert
        assertEquals(expectedEdges.get(0).getEdgeId(), actualEdges.getEdges().get(0).getEdgeId());
    }

    @Test
    void mapMultipleResultSetsToEdgesDTO() throws SQLException{
        // Arrange
        List<EdgeDTO> expectedEdges = null;

        // Act
        EdgesDTO actualEdges = sut.map(resultSet, resultSet);

        // Assert
        Assertions.assertEquals(expectedEdges, actualEdges);
    }

    @Test
    void testMapThrowsSQLException() throws SQLException {
        // Arrange
        when(resultSet.next()).thenThrow(new SQLException());

        // Act & Assert
        assertThrows(SQLException.class, () -> sut.map(resultSet));
    }
}
