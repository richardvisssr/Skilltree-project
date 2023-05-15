package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.SkilltreeDAO;
import nl.han.oose.project.data.dao.StudentDAO;
import nl.han.oose.project.resources.dto.StudentDTO;
import nl.han.oose.project.resources.dto.StudentRequestDTO;
import nl.han.oose.project.resources.dto.StudentsDTO;
import nl.han.oose.project.resources.dto.StudentsRequestDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private StudentDAO studentDAO;

    public StudentsDTO getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    public StudentsDTO getStudentsBySkilltree(int skilltreeId) throws SQLException {
        return studentDAO.getStudentsBySkilltree(skilltreeId);
    }

public void addStudentsToSkilltree(StudentsRequestDTO studentsRequestDTO, int skilltreeId) throws SQLException {
    List<Integer> studentIds = new ArrayList<>();
    for (StudentRequestDTO student : studentsRequestDTO.getStudents()) {
        studentIds.add(student.getId());
    }
    studentDAO.addStudentsToSkilltree(studentIds, skilltreeId);
}
    @Inject
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

}
