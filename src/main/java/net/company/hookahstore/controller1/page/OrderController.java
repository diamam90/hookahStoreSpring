package net.company.hookahstore.controller1.page;
/*
import net.company.hookahstore.entity.Order;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.controller.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;
import net.company.hookahstore.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderController extends AbstractController {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Order order = getOrderService().findOrderById(Long.parseLong(req.getParameter("id")),SessionUtils.getCurrentAccount(req));
        req.setAttribute("order",order);
        RoutingUtils.forwardToPage("order.jsp",req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        long id = getOrderService().makeOrder(shoppingCart,SessionUtils.getCurrentAccount(req));
        SessionUtils.clearCurrentShoppingCart(req);
        RoutingUtils.redirect("/order?id="+id,req,resp);
    }
}
*/