package nl.han.oose.project.resources.dto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDTOTest {
    private UserDTO sut;

    @BeforeEach
    void setup() {
        sut = new UserDTO();
    }

    @Test
    void testConstructorAndGetters() {
        // Arrange
        int id = 1;
        String firstname = "John";
        String lastname = "Doe";
        String email = "John@Doe.com";
        int roleId = 1;
        String password = "test";
        // Act
        sut = new UserDTO(id, firstname, lastname, email, password, roleId);

        // Assert
        assertEquals(id, sut.getId());
        assertEquals(firstname, sut.getFirstname());
        assertEquals(lastname, sut.getLastname());
    }

    @Test
    void testSetters() {
        // Arrange
        int id = 1;
        String firstname = "John";
        String lastname = "Doe";

        // Act
        sut.setId(id);
        sut.setFirstname(firstname);
        sut.setLastname(lastname);

        // Assert
        assertEquals(id, sut.getId());
        assertEquals(firstname, sut.getFirstname());
        assertEquals(lastname, sut.getLastname());
    }

    @Test
    void testDefaultConstructor() {
        // Assert
        assertEquals(0, sut.getId());
        assertNull(sut.getFirstname());
        assertNull(sut.getLastname());
    }
}
