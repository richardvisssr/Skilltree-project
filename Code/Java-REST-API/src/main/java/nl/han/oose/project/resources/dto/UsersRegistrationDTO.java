package nl.han.oose.project.resources.dto;

import java.util.ArrayList;
import java.util.List;

public class UsersRegistrationDTO {

        private List<UserRegistrationDTO> users = new ArrayList<>();

        public UsersRegistrationDTO(){}

        public UsersRegistrationDTO(List<UserRegistrationDTO> users) {
            this.users = users;
        }

        public List<UserRegistrationDTO> getUsers() {
            return users;
        }

        public void setUsers(List<UserRegistrationDTO> users) {
            this.users = users;
        }
    }

