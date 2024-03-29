package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.UserDTO;
import nl.han.oose.project.resources.dto.UsersDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDatamapper implements Datamapper<UsersDTO> {
    @Override
    public UsersDTO map(ResultSet resultSet) throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(
                    new UserDTO(
                            resultSet.getInt("ID"),
                            resultSet.getString("Firstname"),
                            resultSet.getString("Lastname"),
                            resultSet.getString("Email"),
                            resultSet.getInt("RoleID")
                    )
            );
        }
        return new UsersDTO(users);
    }

    @Override
    public UsersDTO map(ResultSet resultSet, ResultSet resultSet2) throws SQLException {
        return null;
    }

}
