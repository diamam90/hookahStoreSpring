package net.company.hookahstore.servlet.page;

import net.company.hookahstore.entity.Account;
import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.servlet.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-account")
public class CreateAccountController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastUrl = req.getHeader("referer");
        req.setAttribute("url",lastUrl);
        RoutingUtils.forwardToPage("create-account.jsp",req,resp);
     }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentAccount currentAccount = createCurrentAccount(req);
        Account account = getAccountService().getAccount(currentAccount.getLogin(), currentAccount.getPassword());
        if (account==null){
            getAccountService().saveAccount(currentAccount);
        }
        RoutingUtils.redirect("/products",req,resp);


    }
}
