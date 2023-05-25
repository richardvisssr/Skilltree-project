package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.StudentDTO;
import nl.han.oose.project.resources.dto.StudentsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDatamapper implements Datamapper<StudentsDTO> {
    @Override
    public StudentsDTO map(ResultSet resultSet) throws SQLException {
        List<StudentDTO> students = new ArrayList<>();
        while (resultSet.next()) {
            students.add(
                    new StudentDTO(
                            resultSet.getInt("ID"),
                            resultSet.getString("Firstname"),
                            resultSet.getString("Lastname")
                    )
            );
        }
        return new StudentsDTO(students);
    }

    @Override
    public StudentsDTO map(ResultSet resultSet, ResultSet resultSet2) throws SQLException {
        return null;
    }

}


