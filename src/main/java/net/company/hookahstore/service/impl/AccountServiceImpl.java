package net.company.hookahstore.service.impl;

import net.company.hookahstore.entity.Account;
import net.company.hookahstore.exception.InternalServerErrorException;
import net.company.hookahstore.jdbc.JDBCUtils;
import net.company.hookahstore.jdbc.ResultSetHandler;
import net.company.hookahstore.jdbc.ResultSetHandlerFactory;
import net.company.hookahstore.service.AccountService;
import net.company.hookahstore.service.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class AccountServiceImpl implements AccountService {
    private final DataSource dataSource;
    private static final Logger LOGGER= LoggerFactory.getLogger(AccountServiceImpl.class);
    private static final ResultSetHandler<Account> accountResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.ACCOUNT_RESULT_SET_HANDLER);

    public AccountServiceImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Account getAccount(String login, String password) {
        try (Connection c = dataSource.getConnection()){
            Account account = JDBCUtils.select(c,"select * from account where login=? and password=?",accountResultSetHandler,login,password);
            if (account == null){
                throw new InternalServerErrorException("Account with login/password: " + login + "/" + password + " not found");
            }
            return account;
        } catch (SQLException e){
            throw new InternalServerErrorException("Can't execute query: ",e);
        }
    }

    @Override
    public void saveAccount(String login, String password, String name, String lastName, String email, String phone) {
        try (Connection c = dataSource.getConnection()){
           Account account = JDBCUtils.insert(c,"insert into account values (nextval('account_seq'),?,?,?,?,?,?)",
                   accountResultSetHandler,name,email,phone,login,password,lastName);
           LOGGER.info("Account {} added",account);
        }catch (SQLException e){
            throw new InternalServerErrorException("Can't save account: " + login + ", cause: "+ e);
        }

    }
}
