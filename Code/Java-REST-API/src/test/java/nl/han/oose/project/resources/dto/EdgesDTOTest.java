package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EdgesDTOTest {

    private EdgesDTO sut;

    @BeforeEach
    void setup() {
        sut = new EdgesDTO();
    }

    @Test
    void testEdges() {
        // Arrange
        var expected = new EdgeDTO();

        // Act
        sut.setEdges(List.of(expected));
        var result = sut.getEdges();

        // Assert
        org.junit.jupiter.api.Assertions.assertEquals(expected, result.get(0));
    }
}
