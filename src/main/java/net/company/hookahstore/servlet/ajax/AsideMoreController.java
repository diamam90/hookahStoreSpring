package net.company.hookahstore.servlet.ajax;

import net.company.hookahstore.Constants;
import net.company.hookahstore.servlet.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/html/more/categories")
public class AsideMoreController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products",getProductService().listProductByAside(req,getPage(req), Constants.ITEMS_PER_PAGE));
        RoutingUtils.forwardToFragment("product-list.jsp",req,resp);
    }
}
