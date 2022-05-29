package net.company.hookahstore.service;

import net.company.hookahstore.entity.Account;
import net.company.hookahstore.model.CurrentAccount;

public interface AccountService {
    CurrentAccount getCurrentAccount(String login, String password);
    void saveAccount(Account account);
    Account getAccountById(Long id);
    Account getAccountByName(String name);
    Account getAccountByLastName(String lastName);
    Account getAccountByEmailContaining(String query);
}
