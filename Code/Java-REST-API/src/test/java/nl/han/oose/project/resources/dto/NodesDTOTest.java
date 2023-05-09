package nl.han.oose.project.resources.dto;

import nl.han.oose.project.resources.dto.NodeDTO;
import nl.han.oose.project.resources.dto.NodesDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class NodesDTOTest {

    private NodesDTO sut;

    @BeforeEach
    void setup() {
        sut = new NodesDTO();
    }

    @Test
    void testNodes() {
        // Arrange
        List<NodeDTO> expected = new ArrayList<>();
        sut.setNodes(expected);

        // Act
        var result = sut.getNodes();

        // Assert
        Assertions.assertEquals(expected, result);
    }
}
