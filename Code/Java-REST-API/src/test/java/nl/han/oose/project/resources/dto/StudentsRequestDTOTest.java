package nl.han.oose.project.resources.dto;

import nl.han.oose.project.resources.dto.StudentRequestDTO;
import nl.han.oose.project.resources.dto.StudentsRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentsRequestDTOTest {

    private StudentsRequestDTO sut;

    @BeforeEach
    void setup() {
        sut = new StudentsRequestDTO();
    }

    @Test
    void testEmptyConstructor() {
        // Act & Assert
        assertEquals(0, sut.getStudents().size());
    }

    @Test
    void testConstructorWithStudents() {
        // Arrange
        List<StudentRequestDTO> expectedStudents = new ArrayList<>();
        expectedStudents.add(new StudentRequestDTO(1));
        expectedStudents.add(new StudentRequestDTO(2));

        // Act
        sut = new StudentsRequestDTO(expectedStudents);

        // Assert
        assertEquals(expectedStudents.size(), sut.getStudents().size());
        assertEquals(expectedStudents.get(0).getId(), sut.getStudents().get(0).getId());
        assertEquals(expectedStudents.get(1).getId(), sut.getStudents().get(1).getId());
    }

    @Test
    void testSetStudents() {
        // Arrange
        List<StudentRequestDTO> expectedStudents = new ArrayList<>();
        expectedStudents.add(new StudentRequestDTO(1));
        expectedStudents.add(new StudentRequestDTO(2));

        // Act
        sut.setStudents(expectedStudents);

        // Assert
        assertEquals(expectedStudents.size(), sut.getStudents().size());
        assertEquals(expectedStudents.get(0).getId(), sut.getStudents().get(0).getId());
        assertEquals(expectedStudents.get(1).getId(), sut.getStudents().get(1).getId());
    }
}

