package nl.han.oose.project.business.services;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;

import java.util.Arrays;

import nl.han.oose.project.data.dao.UserDAO;
import nl.han.oose.project.resources.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService sut;
    private UserDAO userDAO;

    @BeforeEach
    void setup() {
        sut = new UserService();
        userDAO = mock(UserDAO.class, RETURNS_DEEP_STUBS);

        sut.setUserDAO(userDAO);
    }

    @Test
    void testCreateUser() throws Exception {
        // Setup
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setId(1);
        userRegistrationDTO.setPassword("password");
        userRegistrationDTO.setFirstname("firstName");
        userRegistrationDTO.setLastname("lastName");
        userRegistrationDTO.setEmail("email");
        userRegistrationDTO.setRoleId(1);

        // Run the test
        sut.createUser(userRegistrationDTO);

        // Verify the results
        verify(userDAO).createUser(userRegistrationDTO);
    }

    @Test
    void testGetAllUsers() throws Exception {
        // Setup
        final UsersDTO expectedResult = new UsersDTO(Arrays.asList(new UserDTO()));
        when(userDAO.getAllUsers()).thenReturn(expectedResult);

        // Run the test
        final UsersDTO result = sut.getAllUsers();

        // Verify the results
        verify(userDAO).getAllUsers();
    }
}
