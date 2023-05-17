package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
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

    public StudentsDTO updateStudentsToSkilltree(StudentsRequestDTO studentsRequestDTO, int skilltreeId) throws SQLException {
        var currentStudents = getStudentsBySkilltree(skilltreeId);

        List<Integer> newStudents = new ArrayList<>();
        List<Integer> oldStudents = new ArrayList<>();
        List<Integer> deletedStudents = new ArrayList<>();
        for (StudentDTO studentDTO : currentStudents.getStudents()) {
            boolean found = false;
            for (StudentRequestDTO student : studentsRequestDTO.getStudents()) {
                if (studentDTO.getId() == student.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                deletedStudents.add(studentDTO.getId());
            }
        }

        for (StudentRequestDTO student : studentsRequestDTO.getStudents()) {
            boolean found = false;
            for (StudentDTO studentDTO : currentStudents.getStudents()) {
                if (studentDTO.getId() == student.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                newStudents.add(student.getId());
            } else {
                oldStudents.add(student.getId());
            }
        }

        //add to skilltree
        studentDAO.addStudentsToSkilltree(newStudents, skilltreeId);

        //remove from skilltree
        studentDAO.removeStudentsFromSkilltree(deletedStudents, skilltreeId);

        return getStudentsBySkilltree(skilltreeId);
    }
    @Inject
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

}