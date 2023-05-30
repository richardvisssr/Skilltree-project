package nl.han.oose.project.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.UserService;
import nl.han.oose.project.resources.dto.UserRegistrationDTO;
import nl.han.oose.project.resources.dto.UsersDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class UserResourceTest {
    private UserResource sut;
    private UserService userService;

    @BeforeEach
    void setup() {
        sut = new UserResource();
        userService = mock(UserService.class);

        sut.setUserService(userService);
    }

    @Test
    void createUser() throws SQLException {
        // Arrange
        UserRegistrationDTO userRegistrationDTO = mock(UserRegistrationDTO.class);
        var expected = Response.Status.CREATED.getStatusCode();

        // Act
        var result = sut.createUser(userRegistrationDTO);

        // Assert
        Assertions.assertEquals(expected, result.getStatus());
        verify(userService, times(1)).createUser(userRegistrationDTO);
    }

    @Test
    void getAllUsers() {
        try {
            // Arrange
            var expected = Response.Status.OK.getStatusCode();
            UsersDTO usersDTO = mock(UsersDTO.class);
            when(userService.getAllUsers()).thenReturn(usersDTO);

            // Act
            var result = sut.getAllUsers();

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
            verify(userService, times(1)).getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
