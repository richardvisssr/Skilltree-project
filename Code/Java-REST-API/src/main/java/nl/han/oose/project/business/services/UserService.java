package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.resources.dto.UserDTO;
import nl.han.oose.project.data.dao.UserDAO;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;
    public void createUser (UserDTO userDTO) throws SQLException {
        userDAO.createUser(userDTO);
    }


    public UsersDTO getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    };

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
