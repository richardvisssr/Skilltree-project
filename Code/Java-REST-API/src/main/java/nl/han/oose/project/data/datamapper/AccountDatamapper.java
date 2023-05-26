package nl.han.oose.project.data.datamapper;

import nl.han.oose.project.resources.dto.AccountDTO;
import nl.han.oose.project.resources.dto.AccountsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDatamapper implements Datamapper {
    @Override
    public AccountsDTO map(ResultSet resultSet) throws SQLException {
        List<AccountDTO> accounts = new ArrayList<>();
        while (resultSet.next()) {
            accounts.add(
                    new AccountDTO(
                            resultSet.getInt("ID"),
                            resultSet.getString("Email"),
                            resultSet.getInt("RoleID")
                    )
            );
        }
        return new AccountsDTO(accounts);
    }

    @Override
    public Object map(ResultSet resultSet, ResultSet resultSet2) throws SQLException {
        return null;
    }
}
