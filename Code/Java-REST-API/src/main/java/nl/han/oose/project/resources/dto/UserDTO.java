package nl.han.oose.project.resources.dto;

public class UserDTO {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private int roleId;

    public UserDTO() {
        // Default constructor
    }

    // Constructor with all fields
    public UserDTO(int id, String firstname, String lastname, String email, int roleId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.roleId = roleId;
    }

    // Getters and setters for each field
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
