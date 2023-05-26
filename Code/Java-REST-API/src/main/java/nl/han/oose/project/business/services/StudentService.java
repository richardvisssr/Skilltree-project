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

        //add to skilltree
        studentDAO.addStudentsToSkilltree(addNewStudents(currentStudents, studentsRequestDTO), skilltreeId);

        //remove from skilltree
        studentDAO.removeStudentsFromSkilltree(deleteStudents(currentStudents, studentsRequestDTO), skilltreeId);

        return getStudentsBySkilltree(skilltreeId);
    }

    private List<Integer> deleteStudents(StudentsDTO currentStudents, StudentsRequestDTO studentsRequestDTO) {
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

        return deletedStudents;
    }

    private List<Integer> addNewStudents(StudentsDTO currentStudents, StudentsRequestDTO studentsRequestDTO) {
        List<Integer> newStudents = new ArrayList<>();

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
            }
        }

        return newStudents;
    }

    @Inject
    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

}
