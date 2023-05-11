package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class SkilltreesDTOTest {

    @Test
    public void testSkilltreesDTO() {
        // Arrange
        SkilltreesDTO sut = new SkilltreesDTO();

        // Act
        List<SkilltreeDTO> skilltrees = sut.getSkilltrees();

        // Assert
        assertNotNull(skilltrees);
        assertEquals(0, skilltrees.size());
    }

    @Test
    public void testSkilltreesDTOWithList() {
        // Arrange
        SkilltreeDTO skilltreeDTO = new SkilltreeDTO(1, "Programming", "Learn programming skills");
        List<SkilltreeDTO> skilltrees = new ArrayList<>();
        skilltrees.add(skilltreeDTO);

        // Act
        SkilltreesDTO sut = new SkilltreesDTO(skilltrees);

        // Assert
        assertEquals(skilltrees, sut.getSkilltrees());
    }

    @Test
    public void testSetSkilltrees() {
        // Arrange
        SkilltreesDTO sut = new SkilltreesDTO();
        SkilltreeDTO skilltreeDTO = new SkilltreeDTO(1, "Programming", "Learn programming skills");
        List<SkilltreeDTO> skilltrees = new ArrayList<>();
        skilltrees.add(skilltreeDTO);

        // Act
        sut.setSkilltrees(skilltrees);

        // Assert
        assertEquals(skilltrees, sut.getSkilltrees());
    }
}
