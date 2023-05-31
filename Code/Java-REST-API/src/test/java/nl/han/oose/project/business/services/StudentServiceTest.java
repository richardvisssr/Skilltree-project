package nl.han.oose.project.business.services;
import static org.mockito.Answers.RETURNS_DEEP_STUBS;

import java.util.Arrays;
import java.util.List;

import nl.han.oose.project.data.dao.StudentDAO;
import nl.han.oose.project.resources.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
}
