package nl.han.oose.project.resources.dto;

import nl.han.oose.project.resources.dto.StudentDTO;
import nl.han.oose.project.resources.dto.StudentsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StudentsDTOTest {
    private StudentsDTO sut;

    @BeforeEach
    void setup() {
        sut = new StudentsDTO();
    }

    @Test
    void testConstructorAndGetters() {
        // Arrange
        List<StudentDTO> students = new ArrayList<>();
        students.add(new StudentDTO(1, "John", "Doe"));
        students.add(new StudentDTO(2, "Jane", "Smith"));

        // Act
        sut = new StudentsDTO(students);

        // Assert
        assertEquals(students, sut.getStudents());
    }

    @Test
    void testSetters() {
        // Arrange
        List<StudentDTO> students = new ArrayList<>();
        students.add(new StudentDTO(1, "John", "Doe"));
        students.add(new StudentDTO(2, "Jane", "Smith"));

        // Act
        sut.setStudents(students);

        // Assert
        assertEquals(students, sut.getStudents());
    }

    @Test
    void testDefaultConstructor() {
        // Assert
        assertNotNull(sut.getStudents());
        assertTrue(sut.getStudents().isEmpty());
    }
}
