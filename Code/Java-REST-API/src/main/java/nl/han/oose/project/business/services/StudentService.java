package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.SkilltreeDAO;
import nl.han.oose.project.data.dao.StudentDAO;
import nl.han.oose.project.resources.dto.StudentsDTO;

import java.sql.SQLException;

public class StudentService {

    private StudentDAO studentDAO;

    public StudentsDTO getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    @Inject
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

}
