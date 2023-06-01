package nl.han.oose.project.business.services;

import jakarta.inject.Inject;
import nl.han.oose.project.data.dao.AccountDAO;
import nl.han.oose.project.resources.dto.AccountsDTO;

import java.sql.SQLException;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountsDTO getAllAccounts() throws SQLException {
        return accountDAO.getAllAccounts();
    }

    @Inject
    public void setAccountDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

}
