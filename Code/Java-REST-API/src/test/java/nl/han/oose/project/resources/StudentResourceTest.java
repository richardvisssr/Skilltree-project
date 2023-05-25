package nl.han.oose.project.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.project.business.services.StudentService;
import nl.han.oose.project.resources.dto.UserDTO;
import nl.han.oose.project.resources.dto.UsersDTO;
import nl.han.oose.project.resources.dto.StudentsRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class StudentResourceTest {
    private StudentResource sut;
    private UsersDTO studentsDTO;
    private UserDTO studentDTO;
    private StudentService studentService;
    int skilltreeId = 1;
    @BeforeEach
    void setup() {
        sut = new StudentResource();
        studentsDTO = mock(UsersDTO.class);
        studentDTO = mock(UserDTO.class);
        studentService = mock(StudentService.class);

        sut.setStudentService(studentService);
    }

    @Test
    void getAllStudents() {
        try {
            //Arrange
            var expected = Response.Status.OK.getStatusCode();
            when(studentService.getAllStudents()).thenReturn(studentsDTO);

            //Act
            var result = sut.getAllStudents();

            //Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllStudentsWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(studentService.getAllStudents()).thenThrow(new SQLException());

            // Act
            var result = sut.getAllStudents();

            // Arrange
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    void getStudentsBySkilltree() {
        try {
            //Arrange

            var expected = Response.Status.OK.getStatusCode();
            when(studentService.getStudentsBySkilltree(skilltreeId)).thenReturn(studentsDTO);

            //Act
            var result = sut.getStudentsBySkilltree(skilltreeId);

            //Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getStudentsBySkilltreeWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            when(studentService.getStudentsBySkilltree(skilltreeId)).thenThrow(new SQLException());

            // Act
            var result = sut.getStudentsBySkilltree(skilltreeId);

            // Arrange
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }

    @Test
    void addStudentsToSkilltree() {
        // Arrange
        var expected = Response.Status.OK.getStatusCode();
        StudentsRequestDTO studentsRequestDTO = mock(StudentsRequestDTO.class);

        // Act
        var result = sut.updateStudentsToSkilltree(skilltreeId, studentsRequestDTO);

        // Assert
        Assertions.assertEquals(expected, result.getStatus());
    }

    @Test
    void addStudentsToSkilltreeWithException() {
        try {
            // Arrange
            var expected = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            StudentsRequestDTO studentsRequestDTO = mock(StudentsRequestDTO.class);
            doThrow(new SQLException()).when(studentService).updateStudentsToSkilltree(studentsRequestDTO, skilltreeId);

            // Act
            var result = sut.updateStudentsToSkilltree(skilltreeId, studentsRequestDTO);

            // Assert
            Assertions.assertEquals(expected, result.getStatus());
        } catch (SQLException e) {
            fail();
        }
    }

}
