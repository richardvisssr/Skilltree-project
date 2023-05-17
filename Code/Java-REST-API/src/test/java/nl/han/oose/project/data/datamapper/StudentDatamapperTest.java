package nl.han.oose.project.data.datamapper;

import static org.mockito.Mockito.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

import nl.han.oose.project.resources.dto.StudentDTO;
import nl.han.oose.project.resources.dto.StudentsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

    public class StudentDatamapperTest {

        private StudentDatamapper sut;
        private ResultSet resultSet;

        @BeforeEach
        void setup() {
            sut = new StudentDatamapper();
            resultSet = mock(ResultSet.class);
        }

        @Test
        void mapResultSetToStudentsDTO() throws SQLException {
            // Arrange
            List<StudentDTO> expectedStudents = new ArrayList<>();
            expectedStudents.add(new StudentDTO(1, "John", "Doe"));
            expectedStudents.add(new StudentDTO(2, "Jane", "Smith"));
            when(resultSet.next()).thenReturn(true, true, false);
            when(resultSet.getInt("ID")).thenReturn(1, 2);
            when(resultSet.getString("Firstname")).thenReturn("John", "Jane");
            when(resultSet.getString("Lastname")).thenReturn("Doe", "Smith");

            // Act
            StudentsDTO actualStudents = sut.map(resultSet);

            // Assert
            assertEquals(expectedStudents.get(0).getId(), actualStudents.getStudents().get(0).getId());
            assertEquals(expectedStudents.get(1).getId(), actualStudents.getStudents().get(1).getId());
            assertEquals(expectedStudents.get(0).getFirstname(), actualStudents.getStudents().get(0).getFirstname());
            assertEquals(expectedStudents.get(1).getFirstname(), actualStudents.getStudents().get(1).getFirstname());
            assertEquals(expectedStudents.get(0).getLastname(), actualStudents.getStudents().get(0).getLastname());
            assertEquals(expectedStudents.get(1).getLastname(), actualStudents.getStudents().get(1).getLastname());
        }

        @Test
        void mapResultSetToStudentsDTOWithEmptyResultSet() throws SQLException {
            // Arrange
            when(resultSet.next()).thenReturn(false);

            // Act
            StudentsDTO actualStudents = sut.map(resultSet);

            // Assert
            assertEquals(0, actualStudents.getStudents().size());
        }

        @Test
        void mapResultSetToStudentsDTOWithSQLException() throws SQLException {
            // Arrange
            when(resultSet.next()).thenThrow(new SQLException());

            // Act & Assert
            assertThrows(SQLException.class, () -> sut.map(resultSet));
        }
    }

