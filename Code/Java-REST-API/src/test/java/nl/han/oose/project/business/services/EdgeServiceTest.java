package nl.han.oose.project.business.services;

import nl.han.oose.project.data.dao.EdgeDAO;
import nl.han.oose.project.resources.dto.EdgeDTO;
import nl.han.oose.project.resources.dto.EdgesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EdgeServiceTest {
    private EdgeService sut;

    private EdgeDAO edgeDAO;

    @BeforeEach
    void setup() {
        sut = new EdgeService();
        edgeDAO = mock(EdgeDAO.class);

        sut.setEdgeDAO(edgeDAO);
    }

    @Test
    void testCreateEdge() {
        try {
            // Arrange
            var edgeDTO = new EdgeDTO("test", "test", "test", 5);
            var expected = new EdgesDTO();
            expected.setEdges(List.of(edgeDTO));
            when(edgeDAO.createEdge(anyObject(), anyInt())).thenReturn(expected);

            // Act
            var result = sut.createEdge(edgeDTO, 1);

            // Assert
            org.junit.jupiter.api.Assertions.assertEquals(expected.getEdges(), result.getEdges());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAllEdges() {
        try {
            // Arrange
            var edgeDTO = new EdgeDTO("test", "test", "test", 5);
            var expected = new EdgesDTO();
            expected.setEdges(List.of(edgeDTO));
            when(edgeDAO.getAllEdgesFromSkilltree(anyInt())).thenReturn(expected);

            // Act
            var result = sut.getAllEdges(1);

            // Assert
            org.junit.jupiter.api.Assertions.assertEquals(expected.getEdges(), result.getEdges());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteEdge() {
        try {
            // Arrange
                var expected = new EdgesDTO();

                sut.createEdge(new EdgeDTO("test", "test", "test", 5), 1);

                // Act
                sut.deleteEdge("test");

                // Assert
                org.junit.jupiter.api.Assertions.assertEquals(expected.getEdges(), sut.getAllEdges(1).getEdges());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
