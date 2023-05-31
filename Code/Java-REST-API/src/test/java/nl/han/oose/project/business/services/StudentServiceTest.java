package nl.han.oose.project.business.services;

import java.util.Arrays;

import nl.han.oose.project.data.dao.StudentDAO;
import nl.han.oose.project.resources.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    private StudentService sut;
    private StudentDAO studentDAO;

    @BeforeEach
    void setup() {
        sut = new StudentService();
        studentDAO = mock(StudentDAO.class, RETURNS_DEEP_STUBS);

        sut.setStudentDAO(studentDAO);
    }

    @Test
    void getAllStudentsTest() throws Exception {
        // Arrange
        UsersDTO usersDTO = new UsersDTO();
        when(studentDAO.getAllStudents()).thenReturn(usersDTO);

        // Act
        sut.getAllStudents();

        // Assert
        verify(studentDAO).getAllStudents();
    }

    @Test
    void getStudentsBySkilltreeTest() throws Exception {
        // Arrange
        UsersDTO usersDTO = new UsersDTO();
        when(studentDAO.getStudentsBySkilltree(anyInt())).thenReturn(usersDTO);

        // Act
        sut.getStudentsBySkilltree(anyInt());

        // Assert
        verify(studentDAO).getStudentsBySkilltree(anyInt());
    }

    @Test
    void updateStudentsToSkilltreeTest() throws Exception {
        // Arrange
        StudentsRequestDTO studentsRequestDTO = new StudentsRequestDTO();
        UsersDTO usersDTO = new UsersDTO();
        when(studentDAO.getStudentsBySkilltree(anyInt())).thenReturn(usersDTO);

        // Act
        sut.updateStudentsToSkilltree(studentsRequestDTO, anyInt());

        // Assert
        verify(studentDAO).addStudentsToSkilltree(anyList(), anyInt());
        verify(studentDAO).removeStudentsFromSkilltree(anyList(), anyInt());
    }

    @Test
    void deleteStudentsTest() throws Exception {
        // Arrange
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsers(Arrays.asList(new UserDTO(1, "test", "test", "test", 1)));
        StudentsRequestDTO studentsRequestDTO = new StudentsRequestDTO();
        studentsRequestDTO.setStudents(Arrays.asList(new StudentRequestDTO(1)));

        // Act
        sut.deleteStudents(usersDTO, studentsRequestDTO);

        // Assert
        assertFalse(usersDTO.getUsers().contains(new UserDTO(1, "test", "test", "test", 1)));
    }

    @Test
    void addNewStudentsTest() throws Exception {
        // Arrange
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsers(Arrays.asList(new UserDTO(5, "test", "test", "test", 2)));
        StudentsRequestDTO studentsRequestDTO = new StudentsRequestDTO();
        studentsRequestDTO.setStudents(Arrays.asList(new StudentRequestDTO(5)));

        // Act
        sut.addNewStudents(usersDTO, studentsRequestDTO);

        // Assert
        assertTrue(usersDTO.getUsers().contains(new UserDTO(5, "test", "test", "test", 2)));
    }

    @Test
    void addNewStudentsIdNotFoundTest() throws Exception {
        // Arrange
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsers(Arrays.asList(new UserDTO(1, "test", "test", "test", 1)));
        StudentsRequestDTO studentsRequestDTO = new StudentsRequestDTO();
        studentsRequestDTO.setStudents(Arrays.asList(new StudentRequestDTO(2)));

        // Act
        sut.addNewStudents(usersDTO, studentsRequestDTO);

        // Assert
        assertTrue(usersDTO.getUsers().contains(new UserDTO(2, "test", "test", "test", 1)));

    }

    @Test
    void deleteStudentsIdNotFoundTest() throws Exception {
        // Arrange
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsers(Arrays.asList(new UserDTO(1, "test", "test", "test", 1)));
        StudentsRequestDTO studentsRequestDTO = new StudentsRequestDTO();
        studentsRequestDTO.setStudents(Arrays.asList(new StudentRequestDTO(2)));

        // Act
        sut.deleteStudents(usersDTO, studentsRequestDTO);

        // Assert
        assertFalse(usersDTO.getUsers().contains(new UserDTO(1, "test", "test", "test", 1)));
    }
}
