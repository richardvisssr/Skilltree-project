package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.resources.dto.UserDTO;
import nl.han.oose.project.resources.dto.UserRegistrationDTO;
import nl.han.oose.project.resources.dto.UsersDTO;
import nl.han.oose.project.data.dao.UserDAO;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;
    public void createUser (UserRegistrationDTO userRegistrationDTO) throws SQLException {
        userDAO.createUser(userRegistrationDTO);
    }


    public UsersDTO getAllUsers() throws SQLException {
        System.out.println("2");
        return userDAO.getAllUsers();
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
