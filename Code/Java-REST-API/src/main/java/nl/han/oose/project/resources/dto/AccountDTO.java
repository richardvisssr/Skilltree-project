package nl.han.oose.project.resources.dto;

public class AccountDTO {
    private int id;
    private String firstname;
    private String lastname;

    private int roleId;

    // Constructor with all fields
    public AccountDTO(int id, String firstname, String lastname, int roleId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
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

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
