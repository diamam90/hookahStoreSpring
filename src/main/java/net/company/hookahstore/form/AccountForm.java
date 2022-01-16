package net.company.hookahstore.form;

public class AccountForm {
    private String login;
    private String password;
    private String email;
    private String name;
    private String lastName;
    private String phone;

    public AccountForm() {
    }

    public AccountForm(String login, String password, String email, String name, String lastName, String phone) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
