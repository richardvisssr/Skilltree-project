package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsersDTOTest {
    private UsersDTO sut;

    @BeforeEach
    void setup() {
        sut = new UsersDTO();
    }

    @Test
    void testConstructorAndGetters() {
        // Arrange
        List<UserDTO> students = new ArrayList<>();
        students.add(new UserDTO(1, "John", "Doe", "John@Doe.com", 1));
        students.add(new UserDTO(2, "Jane", "Smith", "Jane@Smith.com", 2));

        // Act
        sut = new UsersDTO(students);

        // Assert
        assertEquals(students, sut.getUsers());
    }

    @Test
    void testSetters() {
        // Arrange
        List<UserDTO> students = new ArrayList<>();
        students.add(new UserDTO(1, "John", "Doe", "John@Doe.com", 1));
        students.add(new UserDTO(2, "Jane", "Smith", "Jane@Smith.com", 2));

        // Act
        sut.setUsers(students);

        // Assert
        assertEquals(students, sut.getUsers());
    }

    @Test
    void testDefaultConstructor() {
        // Assert
        assertNotNull(sut.getUsers());
        assertTrue(sut.getUsers().isEmpty());
    }
}
