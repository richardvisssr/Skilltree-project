package nl.han.oose.project.business.services;

import java.util.Arrays;
import java.util.List;

import nl.han.oose.project.data.dao.SkilltreeDAO;
import nl.han.oose.project.resources.dto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
public class SkilltreeServiceTest {

    private SkilltreeService sut;
    private SkilltreeDAO skilltreeDAO;

    @BeforeEach
    void setup() {
        sut = new SkilltreeService();
        skilltreeDAO = mock(SkilltreeDAO.class, RETURNS_DEEP_STUBS);

        sut.setSkilltreeDAO(skilltreeDAO);
    }

//    @Test
//    void testGetAllSkilltrees() {
//        try {
//            // Arrange
//            var skilltreeDTO = new SkilltreeDTO(1, "test", "test");
//            var expected = new SkilltreesDTO();
//            expected.setSkilltrees(List.of(skilltreeDTO));
//            when(skilltreeDAO.getAllSkilltrees(anyInt())).thenReturn(expected);
//
//            // Act
//            var result = sut.getAllSkilltrees(1);
//
//            // Assert
//            Assertions.assertEquals(expected.getSkilltrees(), result.getSkilltrees());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    void testCreateSkilltree() {
        try {
            // Arrange
            var skilltreeDTO = new SkilltreeDTO(1, "test", "test");
            var expected = new SkilltreesDTO();
            expected.setSkilltrees(List.of(skilltreeDTO));
            when(skilltreeDAO.createSkilltree(anyObject(), anyInt())).thenReturn(expected);

            // Act
            var result = sut.createSkilltree(skilltreeDTO, 1);

            // Assert
            Assertions.assertEquals(expected.getSkilltrees(), result.getSkilltrees());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateSkilltree() {
        try {
            // Arrange
            var skilltreeDTO = new SkilltreeDTO(1, "test", "test");
            var expected = new SkilltreesDTO();
            expected.setSkilltrees(Arrays.asList(skilltreeDTO)); // Gebruik Arrays.asList om een lijst te maken
            doReturn(expected).when(skilltreeDAO).updateSkilltree(skilltreeDTO, 1);

            // Act
            var result = sut.updateSkilltree(skilltreeDTO, 1);

            // Assert
            Assertions.assertEquals(expected.getSkilltrees(), result.getSkilltrees());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
