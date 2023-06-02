package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.UserDAO;
import nl.han.oose.project.resources.dto.UserRegistrationDTO;
import nl.han.oose.project.resources.dto.UsersDTO;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;

    public void createUser(UserRegistrationDTO userRegistrationDTO) throws SQLException {
        userDAO.createUser(userRegistrationDTO);
    }


    public UsersDTO getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
