package nl.han.oose.project.business.services;

import nl.han.oose.project.data.dao.SkilltreeDAO;
import nl.han.oose.project.resources.dto.UserDTO;
import nl.han.oose.project.data.dao.UserDAO;

import java.sql.SQLException;

public class UserService {

    private UserDAO userDAO;
    public void createUser (UserDTO userDTO) throws SQLException {
        userDAO.createUser(userDTO);
    }
}
