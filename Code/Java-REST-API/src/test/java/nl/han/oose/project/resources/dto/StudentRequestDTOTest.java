package nl.han.oose.project.resources.dto;
import org.junit.jupiter.api.BeforeEach;
import nl.han.oose.project.resources.dto.StudentRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentRequestDTOTest {

    private StudentRequestDTO sut;

    @BeforeEach
    void setup() {
        sut = new StudentRequestDTO();
    }

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int expected = 1;


        // Act
        sut = new StudentRequestDTO(expected);
        int result = sut.getId();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void testSetters() {
        // Arrange
        int expectedId = 1;
        sut.setId(expectedId);

        // Act
        int result = sut.getId();

        // Assert
        assertEquals(expectedId, result);
    }

    @Test
    void testDefaultConstructor() {
        // Assert
        int expectedId = 0;
        int resultId = sut.getId();
        assertEquals(expectedId, resultId);
    }


}

