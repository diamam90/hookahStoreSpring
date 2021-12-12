package net.company.hookahstore.service;

import net.company.hookahstore.entity.Account;
import net.company.hookahstore.model.CurrentAccount;

public interface AccountService {
    Account getAccount(String login, String password);
    void saveAccount(CurrentAccount currentAccount);
}
