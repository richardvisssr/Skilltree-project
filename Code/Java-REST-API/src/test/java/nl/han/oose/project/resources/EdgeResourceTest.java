package nl.han.oose.project.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.EdgeService;
import nl.han.oose.project.resources.dto.EdgeDTO;
import nl.han.oose.project.resources.dto.EdgesDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class EdgeResourceTest {

    private EdgeResource sut;

    private EdgeService edgeService;

    private EdgeDTO edgeDTO;

    private EdgesDTO edgesDTO;

    private static final int skilltreeId = 1;

    @BeforeEach
    void setup() {
        sut = new EdgeResource();
        edgeService = mock(EdgeService.class);
        edgeDTO = mock(EdgeDTO.class);
        edgesDTO = mock(EdgesDTO.class);

        sut.setEdgeService(edgeService);
    }

    @Test
    void getAllEdges() {
        try {
            // Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(edgeService.getAllEdges(skilltreeId)).thenReturn(edgesDTO);

            // Act
            var result = sut.getAllEdges(skilltreeId);

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllEdgesWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(edgeService.getAllEdges(skilltreeId)).thenThrow(new SQLException());

            // Act
            var result = sut.getAllEdges(skilltreeId);

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    void createEdge() {
        try {
            // Arrange
            var expected = Response.Status.CREATED.getStatusCode();
            when(edgeService.createEdge(edgeDTO, skilltreeId)).thenReturn(edgesDTO);

            // Act
            var result = sut.createEdge(edgeDTO, skilltreeId);

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createEdgeWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(edgeService.createEdge(edgeDTO, skilltreeId)).thenThrow(new SQLException());

            // Act
            var result = sut.createEdge(edgeDTO, skilltreeId);

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    void deleteEdge() {
        try {
            // Arrange
            var expected = Response.Status.OK.getStatusCode();
            doNothing().when(edgeService).deleteEdge("1");
            // Act
            var result = sut.deleteEdge("1");

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteEdgeWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            doThrow(new SQLException()).when(edgeService).deleteEdge("-1");
            // Act
            var result = sut.deleteEdge("-1");

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }
}
