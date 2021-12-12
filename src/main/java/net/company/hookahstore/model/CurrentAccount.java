package net.company.hookahstore.model;

public class CurrentAccount {
    private Long id;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String phone;

    public CurrentAccount(Long id, String login, String password, String name, String lastname, String email, String phone) {
        this(login,password,name,lastname,email,phone);
        this.id=id;
    }
    public CurrentAccount(String login, String password, String name, String lastname, String email, String phone) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.lastName = lastname;
        this.email = email;
        this.phone = phone;
    }

    public CurrentAccount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
