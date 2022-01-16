package net.company.hookahstore.controller;

import net.company.hookahstore.entity.Account;
import net.company.hookahstore.form.AccountForm;
import net.company.hookahstore.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/account/registration")
    public ModelAndView createAccount(){
        ModelAndView modelAndView = new ModelAndView("page-template");
        modelAndView.addObject("currentPage","/WEB-INF/JSP/page/create-account.jsp");
        return modelAndView;
    }

    @PostMapping("/account/create-account")
    public String createAccount(@ModelAttribute AccountForm accountForm){
        Account account = getAccountFromForm(accountForm);
        accountService.saveAccount(account);
        return "redirect:/products";
    }

    public Account getAccountFromForm(AccountForm accountForm){
        Account account = new Account();
        account.setName(accountForm.getName());
        account.setLogin(accountForm.getLogin());
        account.setPassword(accountForm.getPassword());
        account.setLastname(accountForm.getLastName());
        account.setEmail(accountForm.getEmail());
        account.setPhone(accountForm.getPhone());
        return account;
    }
}
