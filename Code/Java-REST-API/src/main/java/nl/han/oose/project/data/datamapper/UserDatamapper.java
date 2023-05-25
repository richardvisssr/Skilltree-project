package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.UserDTO;
import nl.han.oose.project.resources.dto.UsersDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDatamapper implements Datamapper {
    @Override
    public UsersDTO map(ResultSet resultSet) throws SQLException {
        System.out.println("7");
        List<UserDTO> users = new ArrayList<>();
        while (resultSet.next()) {
            System.out.println("kaas");
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
        System.out.println("8");
        return new UsersDTO(users);
    }

}
