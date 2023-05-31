package nl.han.oose.project.resources.dto;

import java.util.ArrayList;
import java.util.List;

public class AccountsDTO {
    private List<AccountDTO> accounts = new ArrayList<>();

    public AccountsDTO() {
    }

    public AccountsDTO(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }
}
