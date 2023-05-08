package nl.han.oose.project.data.dao;

import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.SkilltreeService;
import nl.han.oose.project.resources.SkilltreeResource;
import nl.han.oose.project.resources.dto.SkilltreeDTO;
import nl.han.oose.project.resources.dto.SkilltreesDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SkilltreeDAOTest {
    private SkilltreeResource sut;
    private SkilltreesDTO skilltreesDTO;
    private SkilltreeDTO skilltreeDTO;
    private SkilltreeService skilltreeService;

    private static final int DOCENT_ID = 1;

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
            when(skilltreeService.getAllSkilltrees(DOCENT_ID)).thenReturn(skilltreesDTO);

            //Act
            var result = sut.getAllSkilltrees(DOCENT_ID);

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
            when(skilltreeService.getAllSkilltrees(DOCENT_ID)).thenThrow(new SQLException());

            // Act
            var result = sut.getAllSkilltrees(DOCENT_ID);

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
            when(skilltreeService.createSkilltree(skilltreeDTO, DOCENT_ID)).thenReturn(skilltreesDTO);

            //Act
            var result = sut.createSkilltree(skilltreeDTO, DOCENT_ID);

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
            when(skilltreeService.createSkilltree(skilltreeDTO, DOCENT_ID)).thenThrow(new SQLException());

            // Act
            var result = sut.createSkilltree(skilltreeDTO, DOCENT_ID);

            // Arrange
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }
}
