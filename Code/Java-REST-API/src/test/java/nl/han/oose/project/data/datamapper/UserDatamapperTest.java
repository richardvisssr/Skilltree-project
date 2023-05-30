package nl.han.oose.project.data.datamapper;

import static org.mockito.Mockito.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import nl.han.oose.project.resources.dto.UserDTO;
import nl.han.oose.project.resources.dto.UsersDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class UserDatamapperTest {

        private UserDatamapper sut;
        private ResultSet resultSet;

        @BeforeEach
        void setup() {
            sut = new UserDatamapper();
            resultSet = mock(ResultSet.class);
        }

        @Test
        void mapResultSetToStudentsDTO() throws SQLException {
            // Arrange
            List<UserDTO> expectedStudents = new ArrayList<>();
            expectedStudents.add(new UserDTO(1, "John", "Doe", "John@Doe.com", 1));
            expectedStudents.add(new UserDTO(2, "Jane", "Smith", "Jane@Smith.com", 2));
            when(resultSet.next()).thenReturn(true, true, false);
            when(resultSet.getInt("ID")).thenReturn(1, 2);
            when(resultSet.getString("Firstname")).thenReturn("John", "Jane");
            when(resultSet.getString("Lastname")).thenReturn("Doe", "Smith");
            when(resultSet.getString("Email")).thenReturn("John@Doe.com", "Jane@Smith.com");
            when(resultSet.getInt("RoleID")).thenReturn(1, 2);

            // Act
            UsersDTO actualStudents = sut.map(resultSet);

            // Assert
            assertEquals(expectedStudents.get(0).getId(), actualStudents.getUsers().get(0).getId());
            assertEquals(expectedStudents.get(1).getId(), actualStudents.getUsers().get(1).getId());
            assertEquals(expectedStudents.get(0).getFirstname(), actualStudents.getUsers().get(0).getFirstname());
            assertEquals(expectedStudents.get(1).getFirstname(), actualStudents.getUsers().get(1).getFirstname());
            assertEquals(expectedStudents.get(0).getLastname(), actualStudents.getUsers().get(0).getLastname());
            assertEquals(expectedStudents.get(1).getLastname(), actualStudents.getUsers().get(1).getLastname());
            assertEquals(expectedStudents.get(0).getEmail(), actualStudents.getUsers().get((0)).getEmail());
            assertEquals(expectedStudents.get(1).getEmail(), actualStudents.getUsers().get((1)).getEmail());
            assertEquals(expectedStudents.get(0).getRoleId(), actualStudents.getUsers().get((0)).getRoleId());
            assertEquals(expectedStudents.get(1).getRoleId(), actualStudents.getUsers().get((1)).getRoleId());
            }

        @Test
        void mapResultSetToStudentsDTOWithEmptyResultSet() throws SQLException {
            // Arrange
            when(resultSet.next()).thenReturn(false);

            // Act
            UsersDTO actualStudents = sut.map(resultSet);

            // Assert
            assertEquals(0, actualStudents.getUsers().size());
        }

        @Test
        void mapResultSetToStudentsDTOWithSQLException() throws SQLException {
            // Arrange
            when(resultSet.next()).thenThrow(new SQLException());

            // Act & Assert
            assertThrows(SQLException.class, () -> sut.map(resultSet));
        }
    }

