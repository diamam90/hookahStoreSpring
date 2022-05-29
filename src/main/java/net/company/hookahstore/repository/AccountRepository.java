package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByLoginAndPassword(String login,String password);
    Account findByName(String name);
    Account findByLastname(String lastName);
    Account findByEmailContaining(String query);
}
