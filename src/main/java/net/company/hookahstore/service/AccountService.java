package net.company.hookahstore.service;

import net.company.hookahstore.entity.Account;

public interface AccountService {
    Account getAccount(String login, String password);
    void saveAccount(String login, String password, String name, String lastName, String email, String phone);
}
