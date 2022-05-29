package net.company.hookahstore.model;


import net.company.hookahstore.Constants;
import net.company.hookahstore.entity.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class CurrentAccount extends User {
    private Long id;
    private String name;
    private String email;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public CurrentAccount(Account account) {
        super(account.getName(), account.getPassword(),  Collections.singleton(new SimpleGrantedAuthority(Constants.USER)));
        this.id= account.getId();
        this.email=account.getEmail();
        this.phone=account.getPhone();
    }


    @Override
    public String toString() {
        return "CurrentAccount{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
