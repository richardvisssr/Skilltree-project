package nl.han.oose.project.resources.dto;

import   java.util.ArrayList;
import java.util.List;

public class UsersDTO {
    private List<UserDTO> users = new ArrayList<>();

    public UsersDTO() {
    }

    public UsersDTO(List<UserDTO> users) {
        this.users = users;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
