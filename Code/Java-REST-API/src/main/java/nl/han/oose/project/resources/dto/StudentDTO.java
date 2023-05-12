package nl.han.oose.project.resources.dto;

public class StudentDTO {
    private String firstname;
    private String lastname;

    // Constructors, getters, and setters

    // Default constructor
    public StudentDTO() {
    }

    // Constructor with all fields
    public StudentDTO(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Getters and setters for each field

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}

