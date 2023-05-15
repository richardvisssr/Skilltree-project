package nl.han.oose.project.resources.dto;

public class StudentRequestDTO {

    private int id;

    public StudentRequestDTO(int id) {
        this.id = id;
    }

    public StudentRequestDTO(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
