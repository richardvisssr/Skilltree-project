package nl.han.oose.project.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.SkilltreeService;
import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class SkilltreeResourceTest {
    private SkilltreeResource sut;
    private SkilltreesDTO skilltreesDTO;
    private SkilltreeDTO skilltreeDTO;
    private SkilltreeService skilltreeService;

    private static final int GEBRUIKER_ID = 1;
    private static final int ROL_ID = 1;
    @BeforeEach
    void setup() {
        sut = new SkilltreeResource();
        skilltreesDTO = mock(SkilltreesDTO.class);
        skilltreeDTO = mock(SkilltreeDTO.class);
        skilltreeService = mock(SkilltreeService.class);

        sut.setSkilltreeService(skilltreeService);
    }

    @Test
    void getAllSkilltrees() {
        try {
            //Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(skilltreeService.getAllSkilltrees(GEBRUIKER_ID, ROL_ID)).thenReturn(skilltreesDTO);

            //Act
            var result = sut.getAllSkilltrees(GEBRUIKER_ID, ROL_ID);

            //Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllSkilltreesWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(skilltreeService.getAllSkilltrees(GEBRUIKER_ID, ROL_ID)).thenThrow(new SQLException());

            // Act
            var result = sut.getAllSkilltrees(GEBRUIKER_ID, ROL_ID);

            // Arrange
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    void createSkilltree() {
        try {
            //Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(skilltreeService.createSkilltree(skilltreeDTO, GEBRUIKER_ID)).thenReturn(skilltreesDTO);

            //Act
            var result = sut.createSkilltree(skilltreeDTO, GEBRUIKER_ID);

            //Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createSkilltreeWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(skilltreeService.createSkilltree(skilltreeDTO, GEBRUIKER_ID)).thenThrow(new SQLException());

            // Act
            var result = sut.createSkilltree(skilltreeDTO, GEBRUIKER_ID);

            // Arrange
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    void updateSkilltree() {
        try {
            //Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(skilltreeService.updateSkilltree(skilltreeDTO, GEBRUIKER_ID)).thenReturn(skilltreesDTO);

            //Act
            var result = sut.updateSkilltree(skilltreeDTO, GEBRUIKER_ID);

            //Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateSkilltreeWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(skilltreeService.updateSkilltree(skilltreeDTO, GEBRUIKER_ID)).thenThrow(new SQLException());

            // Act
            var result = sut.updateSkilltree(skilltreeDTO, GEBRUIKER_ID);

            // Arrange
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    void testDeleteSkilltree() {
        int skilltreeId = 1;
        try {
            // Arrange
            var expected = Response.Status.OK.getStatusCode();
            doNothing().when(skilltreeService).deleteSkilltree(skilltreeId);

            // Act
            var result = sut.deleteSkilltree(skilltreeId);

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }
    @Test
    void deleteSkilltreeWithException() {
        int skilltreeId = 1;
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            doThrow(new SQLException()).when(skilltreeService).deleteSkilltree(skilltreeId);

            // Act
            var result = sut.deleteSkilltree(skilltreeId);

            // Arrange
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }
}
