package net.company.hookahstore.controller1.page;
/*
import net.company.hookahstore.Constants;
import net.company.hookahstore.entity.Order;
import net.company.hookahstore.controller.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;
import net.company.hookahstore.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class MyOrdersController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orderList = getOrderService().listMyOrders(SessionUtils.getCurrentAccount(req), 1, Constants.MAX_ORDERS_PER_PAGE);
        req.setAttribute("orderList", orderList);
        RoutingUtils.forwardToPage("/my-orders.jsp", req, resp);
    }
}
*/