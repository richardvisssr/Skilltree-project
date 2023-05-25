package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.UserRegistrationDTO;
import nl.han.oose.project.resources.dto.UsersRegistrationDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserRegistrationDatamapper implements Datamapper{

    @Override
    public UsersRegistrationDTO map(ResultSet resultSet) throws SQLException {
        List<UserRegistrationDTO> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(
                    new UserRegistrationDTO(
                            resultSet.getInt("ID"),
                            resultSet.getString("Firstname"),
                            resultSet.getString("Lastname"),
                            resultSet.getString("Email"),
                            resultSet.getString("Password"),
                            resultSet.getInt("RoleID")
                    )
            );
        }
        return new UsersRegistrationDTO(users);
    }

}
