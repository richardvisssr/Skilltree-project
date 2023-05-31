package nl.han.oose.project.business.services;

import java.util.Arrays;
import java.util.List;

import nl.han.oose.project.data.dao.NodeDAO;
import nl.han.oose.project.resources.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class NodeServiceTest {

    private NodeService sut;
    private NodeDAO nodeDAO;

    @BeforeEach
    void setup() {
        sut = new NodeService();
        nodeDAO = mock(NodeDAO.class, RETURNS_DEEP_STUBS);

        sut.setNodeDAO(nodeDAO);
    }

    @Test
    void testUpdateNode() {
        try {
            // Arrange
            var nodeDTO = new NodeDTO(1, "test", "test", 1.0, 1.0, 1, "test");
            var nodeRequestDTO = new NodeRequestDTO(1, "test", "test", 1.0, 1.0, 1, "test");
            var expected = new NodesDTO();
            expected.setNodes(Arrays.asList(nodeDTO)); // Gebruik Arrays.asList om een lijst te maken
            doReturn(expected).when(nodeDAO).updateNode(nodeRequestDTO, 1);

            // Act
            var result = sut.updateNode(nodeRequestDTO, 1);

            // Assert
            Assertions.assertEquals(expected.getNodes(), result.getNodes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAllNodes() {
        try {
            // Arrange
            var nodeDTO = new NodeDTO(1, "test", "test", 1.0, 1.0, 1, "test");
            var expected = new NodesDTO();
            expected.setNodes(Arrays.asList(nodeDTO)); // Gebruik Arrays.asList om een lijst te maken
            doReturn(expected).when(nodeDAO).getNodesFromSkillTree(1);

            // Act
            var result = sut.getAllNodes(1);

            // Assert
            Assertions.assertEquals(expected.getNodes(), result.getNodes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetHighestNodeId() {
        try {
            // Arrange
            var expected = 1;
            doReturn(expected).when(nodeDAO).getHighestNodeId();

            // Act
            var result = sut.getHighestNodeId();

            // Assert
            Assertions.assertEquals(expected, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateNodesPositions() {
        try {
            // Arrange
            var nodeDTO = new NodeDTO(1, "test", "test", 1.0, 1.0, 1, "test");
            var expected = new NodesDTO();
            expected.setNodes(Arrays.asList(nodeDTO)); // Gebruik Arrays.asList om een lijst te maken
            doReturn(expected).when(nodeDAO).updateNodesPositions(expected, 1);

            // Act
            var result = sut.updateNodesPositions(expected, 1);

            // Assert
            Assertions.assertEquals(expected.getNodes(), result.getNodes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteNode() {
        try {
            // Arrange
            sut.createNode(new NodeRequestDTO(1, "test", "test", 1.0, 1.0, 1, "test"), 1);

            // Act
            sut.deleteNode(1);

            // Assert
            verify(nodeDAO, times(1)).deleteNode(anyInt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreateNode() {
        try {
            // Arrange
            var nodeDTO = new NodeDTO(1, "test", "test", 1.0, 1.0, 1, "test");
            var nodeRequestDTO = new NodeRequestDTO(1, "test", "test", 1.0, 1.0, 1, "test");
            var expected = new NodesDTO();
            expected.setNodes(List.of(nodeDTO));
            when(nodeDAO.createNode(anyObject(), anyInt())).thenReturn(expected);

            // Act
            var result = sut.createNode(nodeRequestDTO, 1);

            // Assert
            Assertions.assertEquals(expected.getNodes(), result.getNodes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
