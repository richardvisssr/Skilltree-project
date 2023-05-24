package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.StudentDAO;
import nl.han.oose.project.resources.dto.UserDTO;
import nl.han.oose.project.resources.dto.StudentRequestDTO;
import nl.han.oose.project.resources.dto.UsersDTO;
import nl.han.oose.project.resources.dto.StudentsRequestDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private StudentDAO studentDAO;

    public UsersDTO getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    public UsersDTO getStudentsBySkilltree(int skilltreeId) throws SQLException {
        return studentDAO.getStudentsBySkilltree(skilltreeId);
    }

    public UsersDTO updateStudentsToSkilltree(StudentsRequestDTO studentsRequestDTO, int skilltreeId) throws SQLException {
        var currentStudents = getStudentsBySkilltree(skilltreeId);

        List<Integer> newStudents = new ArrayList<>();
        List<Integer> oldStudents = new ArrayList<>();
        List<Integer> deletedStudents = new ArrayList<>();
        for (UserDTO userDTO : currentStudents.getUsers()) {
            boolean found = false;
            for (StudentRequestDTO student : studentsRequestDTO.getStudents()) {
                if (userDTO.getId() == student.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                deletedStudents.add(userDTO.getId());
            }
        }

        for (StudentRequestDTO student : studentsRequestDTO.getStudents()) {
            boolean found = false;
            for (UserDTO userDTO : currentStudents.getUsers()) {
                if (userDTO.getId() == student.getId()) {
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
