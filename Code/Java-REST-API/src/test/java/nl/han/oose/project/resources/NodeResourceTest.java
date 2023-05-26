package nl.han.oose.project.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.NodeService;
import nl.han.oose.project.data.dao.NodeDAO;
import nl.han.oose.project.resources.dto.NodeDTO;
import nl.han.oose.project.resources.dto.NodesDTO;
import nl.han.oose.project.resources.dto.NodeRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class NodeResourceTest {
    private NodeResource sut;
    private NodeDTO nodeDTO;
    private NodesDTO nodesDTO;
    private NodeRequestDTO nodeRequestDTO;
    private NodeService nodeService;

    private static final int GEBRUIKER_ID = 1;
    private static final int SKILLTREE_ID = 1;

    @BeforeEach
    void setup() {
        sut = new NodeResource();
        nodesDTO = mock(NodesDTO.class);
        nodeDTO = mock(NodeDTO.class);
        nodeRequestDTO = mock(NodeRequestDTO.class);
        nodeService = mock(NodeService.class);

        sut.setNodeService(nodeService);
    }

    @Test
    void createNode() {
        try {
            //Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(nodeService.createNode(nodeRequestDTO, GEBRUIKER_ID)).thenReturn(nodesDTO);

            //Act
            var result = sut.createNode(nodeRequestDTO, GEBRUIKER_ID);

            //Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createNodeWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(nodeService.createNode(nodeRequestDTO, GEBRUIKER_ID)).thenThrow(new SQLException());

            // Act
            var result = sut.createNode(nodeRequestDTO, GEBRUIKER_ID);

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void updateNode(){
        try {
            //Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(nodeService.updateNode(nodeRequestDTO, GEBRUIKER_ID)).thenReturn(nodesDTO);

            //Act
            var result = sut.updateNode(nodeRequestDTO, GEBRUIKER_ID);
            //Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testGetAllNodes() {
        try {
            // Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(nodeService.getAllNodes(SKILLTREE_ID)).thenReturn(nodesDTO);

            // Act
            var result = sut.getAllNodes(SKILLTREE_ID);

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    void testGetHighestNodeId() {
        int highestNodeId = 2;
        try {
            // Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(nodeService.getHighestNodeId()).thenReturn(highestNodeId);

            // Act
            var result = sut.getHighestNodeId();

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    void updateNodesPositions() {
        try {
            //Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(nodeService.updateNodesPositions(nodesDTO, GEBRUIKER_ID)).thenReturn(nodesDTO);

            //Act
            var result = sut.updateNodesPositions(nodesDTO, GEBRUIKER_ID);

            //Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateNodesPositionsWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(nodeService.updateNodesPositions(nodesDTO, GEBRUIKER_ID)).thenThrow(new SQLException());

            // Act
            var result = sut.updateNodesPositions(nodesDTO, GEBRUIKER_ID);

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
