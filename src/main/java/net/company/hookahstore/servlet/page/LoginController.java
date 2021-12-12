package net.company.hookahstore.servlet.page;

import net.company.hookahstore.Constants;
import net.company.hookahstore.entity.Account;
import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.servlet.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;
import net.company.hookahstore.utils.SessionUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentAccount currentAccount = createCurrentAccount(req);
        if (currentAccount != null) {
            Account account = getAccountService().getAccount(currentAccount.getLogin(), currentAccount.getPassword());
            if (account != null) {
                currentAccount.setId(account.getId());
                currentAccount.setEmail(account.getEmail());
                currentAccount.setName(account.getName());
                currentAccount.setLastName(account.getLastname());
                currentAccount.setEmail(account.getEmail());
                currentAccount.setPhone(account.getPhone());
                SessionUtils.setCurrentAccount(req,currentAccount);
                RoutingUtils.redirect("/shopping-cart", req, resp);
            } else {
                RoutingUtils.redirect("/products", req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtils.forwardToPage("login.jsp",req,resp);
    }
}
