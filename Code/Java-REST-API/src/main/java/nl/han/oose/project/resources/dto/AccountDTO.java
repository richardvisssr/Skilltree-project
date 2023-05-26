package nl.han.oose.project.resources.dto;

public class AccountDTO {
    private int id;

    private String email;

    private int roleId;

    // Constructor with all fields
    public AccountDTO(int id, String email, int roleId) {
        this.id = id;
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

    public String getEmail() {
        return this.email;
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
