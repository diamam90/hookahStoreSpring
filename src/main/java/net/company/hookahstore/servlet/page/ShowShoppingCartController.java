package net.company.hookahstore.servlet.page;

import net.company.hookahstore.servlet.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;
import net.company.hookahstore.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shopping-cart")
public class ShowShoppingCartController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if (SessionUtils.isCurrentShoppingCartCreated(req)){
           RoutingUtils.forwardToPage("shopping-cart.jsp",req,resp);
       }else {
            RoutingUtils.redirect("/products",req,resp);
       }

    }
}
