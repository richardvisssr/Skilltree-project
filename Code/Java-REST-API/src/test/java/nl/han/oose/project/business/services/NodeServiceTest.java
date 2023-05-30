package nl.han.oose.project.business.services;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import nl.han.oose.project.data.dao.NodeDAO;
import nl.han.oose.project.resources.dto.NodeDTO;
import nl.han.oose.project.resources.dto.NodeRequestDTO;
import nl.han.oose.project.resources.dto.NodesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeServiceTest {

    private NodeService sut;
    private NodeDAO nodeDAO;

    @BeforeEach
    void setup() {
        sut = new NodeService();
//        nodeDAO = mock(NodeDAO.class);
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
            org.junit.jupiter.api.Assertions.assertEquals(expected.getNodes(), result.getNodes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
