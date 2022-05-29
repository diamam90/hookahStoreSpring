package net.company.hookahstore.service.impl;

import net.company.hookahstore.entity.Account;
import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentAccountDelailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentAccountDelailsService.class);

    @Autowired
    AccountService accountService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = getAccount(username);
        if (account != null) {
            return new CurrentAccount(account);
        } else {
            LOGGER.error("User with name" + username + " not found");
            throw new UsernameNotFoundException("User with name" + username + " not found");
        }
    }

    private Account getAccount(String name) {
        Account account = accountService.getAccountByName(name);
        if (account == null) {
            account = accountService.getAccountByLastName(name);
        }
        if (account == null) {
            account = accountService.getAccountByEmailContaining(name);
        }
        return account;
    }
}
