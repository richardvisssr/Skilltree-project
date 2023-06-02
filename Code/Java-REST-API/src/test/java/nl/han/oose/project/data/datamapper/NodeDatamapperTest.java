package nl.han.oose.project.data.datamapper;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import nl.han.oose.project.resources.dto.AssessmentCriteriaDTO;
import nl.han.oose.project.resources.dto.NodeDTO;
import nl.han.oose.project.resources.dto.NodesDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NodeDatamapperTest {

    private NodeDatamapper sut;
    private ResultSet resultSet;
    private ResultSet assessmentCriteriaResultSet;

    @BeforeEach
    void setup() {
        sut = new NodeDatamapper();
        resultSet = mock(ResultSet.class);
        assessmentCriteriaResultSet = mock(ResultSet.class);
    }

    @Test
    void testMap() throws SQLException {
        // Arrange
        List<NodeDTO> expectedNodes = new ArrayList<>();
        expectedNodes.add(new NodeDTO(1, "Test Node 1", "Description 1", 5, 5, 5, "test"));
        expectedNodes.add(new NodeDTO(2, "Test Node 2", "Description 2",  5, 5, 5, "test"));
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("ID")).thenReturn(1, 2);
        when(resultSet.getString("Skill")).thenReturn("Test Node 1", "Test Node 2");
        when(resultSet.getString("NodeDescription")).thenReturn("Description 1", "Description 2");
        when(resultSet.getInt("SkilltreeID")).thenReturn(5, 5);
        when(resultSet.getInt("PositionX")).thenReturn(5, 5);
        when(resultSet.getInt("PositionY")).thenReturn(5, 5);
        when(resultSet.getString("LearningOutcomeDescription")).thenReturn("test", "test");

        // Act
        NodesDTO actualNodes = sut.map(resultSet, resultSet);

        // Assert
        assertEquals(expectedNodes.get(0).getID(), actualNodes.getNodes().get(0).getID());
        assertEquals(expectedNodes.get(1).getID(), actualNodes.getNodes().get(1).getID());
        assertEquals(expectedNodes.get(0).getSkill(), actualNodes.getNodes().get(0).getSkill());
        assertEquals(expectedNodes.get(1).getSkill(), actualNodes.getNodes().get(1).getSkill());
        assertEquals(expectedNodes.get(0).getDescription(), actualNodes.getNodes().get(0).getDescription());
        assertEquals(expectedNodes.get(1).getDescription(), actualNodes.getNodes().get(1).getDescription());
        assertEquals(expectedNodes.get(0).getPositionX(), actualNodes.getNodes().get(0).getPositionX());
        assertEquals(expectedNodes.get(1).getPositionX(), actualNodes.getNodes().get(1).getPositionX());
        assertEquals(expectedNodes.get(0).getPositionY(), actualNodes.getNodes().get(0).getPositionY());
        assertEquals(expectedNodes.get(1).getPositionY(), actualNodes.getNodes().get(1).getPositionY());
        assertEquals(expectedNodes.get(0).getSkilltreeId(), actualNodes.getNodes().get(0).getSkilltreeId());
        assertEquals(expectedNodes.get(1).getSkilltreeId(), actualNodes.getNodes().get(1).getSkilltreeId());
    }

    @Test
    void testMapWithEmptyResultSet() throws SQLException {
        // Arrange
        when(resultSet.next()).thenReturn(false);

        // Act
        NodesDTO actualNodes = sut.map(resultSet, resultSet);

        // Assert
        assertEquals(0, actualNodes.getNodes().size());
    }

    @Test
    void testMapWithSQLException() throws SQLException {
        // Arrange
        when(resultSet.next()).thenThrow(new SQLException());

        // Act & Assert
        assertThrows(SQLException.class, () -> sut.map(resultSet, resultSet));
    }

    @Test
    void mapOneResultSetToNodesDTO() throws SQLException{
        // Arrange
        List<NodeDTO> expectedNodes = null;

        // Act
        NodesDTO actualNodes = sut.map(resultSet);

        // Assert
        Assertions.assertEquals(expectedNodes, actualNodes);
    }

    @Test
    void testMapWithAssessmentCriteria() throws SQLException {
        // Arrange
        List<NodeDTO> expectedNodes = new ArrayList<>();
        NodeDTO node1 = new NodeDTO(1, "Test Node 1", "Description 1", 5, 5, 5, "test");
        NodeDTO node2 = new NodeDTO(2, "Test Node 2", "Description 2", 5, 5, 5, "test");
        node1.getAssessmentCriteria().add(new AssessmentCriteriaDTO("Criteria 1", "A"));
        node2.getAssessmentCriteria().add(new AssessmentCriteriaDTO("Criteria 2", "B"));
        expectedNodes.add(node1);
        expectedNodes.add(node2);

        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("ID")).thenReturn(1, 2);
        when(resultSet.getString("Skill")).thenReturn("Test Node 1", "Test Node 2");
        when(resultSet.getString("NodeDescription")).thenReturn("Description 1", "Description 2");
        when(resultSet.getInt("SkilltreeID")).thenReturn(5, 5);
        when(resultSet.getInt("PositionX")).thenReturn(5, 5);
        when(resultSet.getInt("PositionY")).thenReturn(5, 5);
        when(resultSet.getString("LearningOutcomeDescription")).thenReturn("test", "test");

        when(assessmentCriteriaResultSet.next()).thenReturn(true, true, false);
        when(assessmentCriteriaResultSet.getInt("NodeID")).thenReturn(1, 2);
        when(assessmentCriteriaResultSet.getString("AcceptationCriteriaDescription")).thenReturn("Criteria 1", "Criteria 2");
        when(assessmentCriteriaResultSet.getString("character")).thenReturn("A", "B");

        // Act
        NodesDTO actualNodes = sut.map(resultSet, assessmentCriteriaResultSet);

        // Assert
        assertEquals(expectedNodes.get(0).getID(), actualNodes.getNodes().get(0).getID());
        assertEquals(expectedNodes.get(1).getID(), actualNodes.getNodes().get(1).getID());
        assertEquals(expectedNodes.get(0).getSkill(), actualNodes.getNodes().get(0).getSkill());
        assertEquals(expectedNodes.get(1).getSkill(), actualNodes.getNodes().get(1).getSkill());
        assertEquals(expectedNodes.get(0).getDescription(), actualNodes.getNodes().get(0).getDescription());
        assertEquals(expectedNodes.get(1).getDescription(), actualNodes.getNodes().get(1).getDescription());
        assertEquals(expectedNodes.get(0).getPositionX(), actualNodes.getNodes().get(0).getPositionX());
        assertEquals(expectedNodes.get(1).getPositionX(), actualNodes.getNodes().get(1).getPositionX());
        assertEquals(expectedNodes.get(0).getPositionY(), actualNodes.getNodes().get(0).getPositionY());
        assertEquals(expectedNodes.get(1).getPositionY(), actualNodes.getNodes().get(1).getPositionY());
        assertEquals(expectedNodes.get(0).getSkilltreeId(), actualNodes.getNodes().get(0).getSkilltreeId());
        assertEquals(expectedNodes.get(1).getSkilltreeId(), actualNodes.getNodes().get(1).getSkilltreeId());
        assertEquals(expectedNodes.get(0).getAssessmentCriteria().size(), actualNodes.getNodes().get(0).getAssessmentCriteria().size());
        assertEquals(expectedNodes.get(0).getAssessmentCriteria().get(0).getDescription(), actualNodes.getNodes().get(0).getAssessmentCriteria().get(0).getDescription());
        assertEquals(expectedNodes.get(0).getAssessmentCriteria().get(0).getCharacter(), actualNodes.getNodes().get(0).getAssessmentCriteria().get(0).getCharacter());
        assertEquals(expectedNodes.get(1).getAssessmentCriteria().size(), actualNodes.getNodes().get(1).getAssessmentCriteria().size());
        assertEquals(expectedNodes.get(1).getAssessmentCriteria().get(0).getDescription(), actualNodes.getNodes().get(1).getAssessmentCriteria().get(0).getDescription());
        assertEquals(expectedNodes.get(1).getAssessmentCriteria().get(0).getCharacter(), actualNodes.getNodes().get(1).getAssessmentCriteria().get(0).getCharacter());
    }

    @Test
    void testMapWithAssessmentCriteriaWithSQLException() throws SQLException {
        // Arrange
        List<NodeDTO> expectedNodes = new ArrayList<>();
        NodeDTO node1 = new NodeDTO(1, "Test Node 1", "Description 1", 5, 5, 5, "test");
        NodeDTO node2 = new NodeDTO(2, "Test Node 2", "Description 2", 5, 5, 5, "test");
        node1.getAssessmentCriteria().add(new AssessmentCriteriaDTO("Criteria 1", "A"));
        node2.getAssessmentCriteria().add(new AssessmentCriteriaDTO("Criteria 2", "B"));
        expectedNodes.add(node1);
        expectedNodes.add(node2);

        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("ID")).thenReturn(1, 2);
        when(resultSet.getString("Skill")).thenReturn("Test Node 1", "Test Node 2");
        when(resultSet.getString("NodeDescription")).thenReturn("Description 1", "Description 2");
        when(resultSet.getInt("SkilltreeID")).thenReturn(5, 5);
        when(resultSet.getInt("PositionX")).thenReturn(5, 5);
        when(resultSet.getInt("PositionY")).thenReturn(5, 5);
        when(resultSet.getString("LearningOutcomeDescription")).thenReturn("test", "test");

        when(assessmentCriteriaResultSet.next()).thenReturn(true).thenThrow(new SQLException());
        when(assessmentCriteriaResultSet.getInt("NodeID")).thenReturn(1, 2);
        when(assessmentCriteriaResultSet.getString("AcceptationCriteriaDescription")).thenReturn("Criteria 1", "Criteria 2");
        when(assessmentCriteriaResultSet.getString("character")).thenReturn("A", "B");

        // Act & Assert
        assertThrows(SQLException.class, () -> sut.map(resultSet, assessmentCriteriaResultSet));
    }
}
