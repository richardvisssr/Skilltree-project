package nl.han.oose.project.resources.dto;

import java.util.ArrayList;
import java.util.List;

public class StudentsRequestDTO {
    private List<StudentRequestDTO> students = new ArrayList<>();

    public StudentsRequestDTO() {
        // Default constructor
    }

    public StudentsRequestDTO(List<StudentRequestDTO> students) {
        this.students = students;
    }

    public List<StudentRequestDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentRequestDTO> students) {
        this.students = students;
    }
}
