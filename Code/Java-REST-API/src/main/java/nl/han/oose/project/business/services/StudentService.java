package nl.han.oose.project.business.services;

import nl.han.oose.project.data.dao.StudentDAO;
import nl.han.oose.project.resources.dto.StudentsDTO;

import java.sql.SQLException;

public class StudentService {

    private StudentDAO studentDAO;

    public StudentsDTO getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }
}
