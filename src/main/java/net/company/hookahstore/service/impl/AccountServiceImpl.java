package net.company.hookahstore.service.impl;

import net.company.hookahstore.entity.Account;
import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.repository.AccountRepository;
import net.company.hookahstore.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER= LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountRepository accountRepository;

    @Override
    public CurrentAccount getCurrentAccount(String login, String password) {
        Account account = accountRepository.findByLoginAndPassword(login,password);
        if (account==null){
            return null;
        }
        CurrentAccount current = new CurrentAccount();
        current.setId(account.getId());
        current.setEmail(account.getEmail());
        current.setName(account.getName());
        current.setPhone(account.getPhone());
      return current;
    }

    @Override
    public void saveAccount(Account account) {
        if (accountRepository.findByLoginAndPassword(account.getLogin(), account.getPassword())==null){
            accountRepository.save(account);
        }
        LOGGER.info("account {} has been saved",account);
    }
}
