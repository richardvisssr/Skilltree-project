package nl.han.oose.project.resources.dto;

import java.util.ArrayList;
import java.util.List;

public class StudentsDTO {
    private List<StudentDTO> students = new ArrayList<>();

    public StudentsDTO() {
    }

    public StudentsDTO(List<StudentDTO> students) {
        this.students = students;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}

