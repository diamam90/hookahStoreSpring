package net.company.hookahstore.servlet.page;

import net.company.hookahstore.entity.Account;
import net.company.hookahstore.form.LoginForm;
import net.company.hookahstore.servlet.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LoginForm loginForm = createLoginForm(req);
        if (loginForm != null) {
            Account account = getAccountService().getAccount(loginForm.getLogin(), loginForm.getPassword());
            if (account != null) {
                req.getSession().setAttribute("currentAccount", account);
            }
        }
        RoutingUtils.redirect(req.getHeader("referer"), req, resp);
    }
}
