package nl.han.oose.project.data.datamapper;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import static org.junit.jupiter.api.Assertions.assertEquals;

public class SkilltreeDatamapperTest {

    private SkilltreeDatamapper sut;
    private ResultSet resultSet;

    @BeforeEach
    void setup() {
        sut = new SkilltreeDatamapper();
        resultSet = mock(ResultSet.class);
    }

    @Test
    void testMap() throws SQLException {
        // Arrange
        List<SkilltreeDTO> expectedSkilltrees = new ArrayList<>();
        expectedSkilltrees.add(new SkilltreeDTO(1, "Test Skilltree 1", "Description 1"));
        expectedSkilltrees.add(new SkilltreeDTO(2, "Test Skilltree 2", "Description 2"));
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("id")).thenReturn(1, 2);
        when(resultSet.getString("title")).thenReturn("Test Skilltree 1", "Test Skilltree 2");
        when(resultSet.getString("description")).thenReturn("Description 1", "Description 2");

        // Act
        SkilltreesDTO actualSkilltrees = sut.map(resultSet);

        // Assert
        assertEquals(expectedSkilltrees.get(0).getId(), actualSkilltrees.getSkilltrees().get(0).getId());
        assertEquals(expectedSkilltrees.get(1).getId(), actualSkilltrees.getSkilltrees().get(1).getId());
        assertEquals(expectedSkilltrees.get(0).getTitle(), actualSkilltrees.getSkilltrees().get(0).getTitle());
        assertEquals(expectedSkilltrees.get(1).getTitle(), actualSkilltrees.getSkilltrees().get(1).getTitle());
        assertEquals(expectedSkilltrees.get(0).getDescription(), actualSkilltrees.getSkilltrees().get(0).getDescription());
        assertEquals(expectedSkilltrees.get(1).getDescription(), actualSkilltrees.getSkilltrees().get(1).getDescription());
    }

    @Test
    void testMapWithEmptyResultSet() throws SQLException {
        // Arrange
        when(resultSet.next()).thenReturn(false);

        // Act
        SkilltreesDTO actualSkilltrees = sut.map(resultSet);

        // Assert
        assertEquals(0, actualSkilltrees.getSkilltrees().size());
    }

    @Test
    void testMapWithSQLException() throws SQLException {
        // Arrange
        when(resultSet.next()).thenThrow(new SQLException());

        // Act & Assert
        assertThrows(SQLException.class, () -> sut.map(resultSet));
    }

    @Test
    void mapMultipleResultSetsToSkilltreesDTO() throws SQLException{
        // Arrange
        List<SkilltreeDTO> expectedSkilltrees = null;

        // Act
        SkilltreesDTO actualSkilltrees = sut.map(resultSet, resultSet);

        // Assert
        Assertions.assertEquals(expectedSkilltrees, actualSkilltrees);
    }
}
