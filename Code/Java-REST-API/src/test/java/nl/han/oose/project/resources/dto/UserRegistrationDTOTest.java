package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationDTOTest {
    UserRegistrationDTO sut;

    @BeforeEach
    void setup() {
        sut = new UserRegistrationDTO();
    }

    @Test
    void testConstructorAndGetters() {
        int id = 1;
        String firstname = "John";
        String lastname = "Doe";
        String email = "john.doe@example.com";
        String password = "password";
        int roleId = 2;

        sut = new UserRegistrationDTO(id, firstname, lastname, email, password, roleId);

        assertEquals(id, sut.getId());
        assertEquals(firstname, sut.getFirstname());
        assertEquals(lastname, sut.getLastname());
        assertEquals(email, sut.getEmail());
        assertEquals(password, sut.getPassword());
        assertEquals(roleId, sut.getRoleId());
    }

    @Test
    void testSetters() {
        int id = 1;
        String firstname = "John";
        String lastname = "Doe";
        String email = "john.doe@example.com";
        String password = "password";
        int roleId = 2;

        sut.setId(id);
        sut.setFirstname(firstname);
        sut.setLastname(lastname);
        sut.setEmail(email);
        sut.setPassword(password);
        sut.setRoleId(roleId);

        assertEquals(id, sut.getId());
        assertEquals(firstname, sut.getFirstname());
        assertEquals(lastname, sut.getLastname());
        assertEquals(email, sut.getEmail());
        assertEquals(password, sut.getPassword());
        assertEquals(roleId, sut.getRoleId());
    }
}
