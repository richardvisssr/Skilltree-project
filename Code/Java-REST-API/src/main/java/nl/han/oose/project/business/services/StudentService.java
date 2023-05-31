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

        //add to skilltree
        studentDAO.addStudentsToSkilltree(addNewStudents(currentStudents, studentsRequestDTO), skilltreeId);

        //remove from skilltree
        studentDAO.removeStudentsFromSkilltree(deleteStudents(currentStudents, studentsRequestDTO), skilltreeId);

        return getStudentsBySkilltree(skilltreeId);
    }

    public List<Integer> deleteStudents(UsersDTO currentStudents, StudentsRequestDTO studentsRequestDTO) {
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

        return deletedStudents;
    }

    public List<Integer> addNewStudents(UsersDTO currentStudents, StudentsRequestDTO studentsRequestDTO) {
        List<Integer> newStudents = new ArrayList<>();

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
            }
        }

        return newStudents;
    }

    @Inject
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

}
