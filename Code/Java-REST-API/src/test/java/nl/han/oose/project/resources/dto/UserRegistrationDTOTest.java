package nl.han.oose.project.resources.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationDTOTest {
    UserRegistrationDTO sut;

    private final int ID = 1;
    private final String FIRSTNAME = "John";
    private final String LASTNAME = "Doe";
    private final String EMAIL = "john.doe@example.com";
    private final String PASSWORD = "password";
    private final int ROLE_ID = 2;

    @BeforeEach
    void setup() {
        sut = new UserRegistrationDTO(ID, FIRSTNAME, LASTNAME, EMAIL, PASSWORD, ROLE_ID);
    }

    @Test
    void testConstructorAndGetters() {

        assertAll("Constructor and getters",
                () -> assertEquals(ID, sut.getId()),
                () -> assertEquals(FIRSTNAME, sut.getFirstname()),
                () -> assertEquals(LASTNAME, sut.getLastname()),
                () -> assertEquals(EMAIL, sut.getEmail()),
                () -> assertEquals(PASSWORD, sut.getPassword()),
                () -> assertEquals(ROLE_ID, sut.getRoleId())
        );
    }

    @Test
    void testSetters() {

        sut.setId(ID);
        sut.setFirstname(FIRSTNAME);
        sut.setLastname(LASTNAME);
        sut.setEmail(EMAIL);
        sut.setPassword(PASSWORD);
        sut.setRoleId(ROLE_ID);

        assertEquals(ID, sut.getId());
        assertEquals(FIRSTNAME, sut.getFirstname());
        assertEquals(LASTNAME, sut.getLastname());
        assertEquals(EMAIL, sut.getEmail());
        assertEquals(PASSWORD, sut.getPassword());
        assertEquals(ROLE_ID, sut.getRoleId());
    }
}
