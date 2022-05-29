package net.company.hookahstore.service.impl;

import net.company.hookahstore.entity.Account;
import net.company.hookahstore.exception.ResourceNotFoundException;
import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.repository.AccountRepository;
import net.company.hookahstore.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountRepository accountRepository;

    @Override
    public CurrentAccount getCurrentAccount(String login, String password) {
        Account account = accountRepository.findByLoginAndPassword(login, password);
        if (account == null) {
            return null;
        }
        CurrentAccount current = new CurrentAccount(account);
        current.setId(account.getId());
        current.setEmail(account.getEmail());
        current.setPhone(account.getPhone());
        return current;
    }

    @Override
    public void saveAccount(Account account) {
        if (accountRepository.findByLoginAndPassword(account.getLogin(), account.getPassword()) == null) {
            accountRepository.save(account);
        }
        LOGGER.info("account {} has been saved", account);
    }

    @Override
    public Account getAccountById(Long id) {
        Account account = accountRepository.getById(id);
        if (account != null) {
            return account;
        } else {
            throw new ResourceNotFoundException("Account with id: " + id + "not found");
        }
    }

    public Account getAccountByName(String name){
        return accountRepository.findByName(name);
    }

    @Override
    public Account getAccountByLastName(String lastName) {
        return accountRepository.findByLastname(lastName);
    }

    @Override
    public Account getAccountByEmailContaining(String query) {
        return accountRepository.findByEmailContaining(query);
    }
}
