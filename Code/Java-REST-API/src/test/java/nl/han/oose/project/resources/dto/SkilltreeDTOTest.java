package nl.han.oose.project.resources.dto;

import nl.han.oose.project.resources.dto.EdgeDTO;
import nl.han.oose.project.resources.dto.NodeDTO;
import nl.han.oose.project.resources.dto.SkilltreeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SkilltreeDTOTest {
    SkilltreeDTO sut;

    @BeforeEach
    void setup() {
        // Arrange
        sut = new SkilltreeDTO();
    }

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int id = 1;
        String title = "Test Title";
        String description = "Test Description";
        List<NodeDTO> nodes = new ArrayList<>();
        List<EdgeDTO> edges = new ArrayList<>();
        sut = new SkilltreeDTO(id, title, description);

        // Assert
        assertEquals(id, sut.getId());
        assertEquals(title, sut.getTitle());
        assertEquals(description, sut.getDescription());
        assertEquals(nodes, sut.getNodes());
        assertEquals(edges, sut.getEdges());
    }

    @Test
    void testSetters() {
        // Arrange
        int id = 1;
        String title = "Test Title";
        String description = "Test Description";
        List<NodeDTO> nodes = new ArrayList<>();
        List<EdgeDTO> edges = new ArrayList<>();

        // Act
        sut.setId(id);
        sut.setTitle(title);
        sut.setDescription(description);
        sut.setNodes(nodes);
        sut.setEdges(edges);

        // Assert
        assertEquals(id, sut.getId());
        assertEquals(title, sut.getTitle());
        assertEquals(description, sut.getDescription());
        assertEquals(nodes, sut.getNodes());
        assertEquals(edges, sut.getEdges());
    }

    @Test
    void testEmptyConstructor() {
        // Assert
        assertEquals(0.0, sut.getId());
        assertNull(sut.getTitle());
        assertNull(sut.getDescription());
        assertNotNull(sut.getNodes());
        assertNotNull(sut.getEdges());
        assertTrue(sut.getNodes().isEmpty());
        assertTrue(sut.getEdges().isEmpty());
    }
}
