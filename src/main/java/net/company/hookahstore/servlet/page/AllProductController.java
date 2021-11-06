package net.company.hookahstore.servlet.page;

import net.company.hookahstore.Constants;
import net.company.hookahstore.servlet.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/products")
public class AllProductController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", getProductService().listAllProduct(1, Constants.ITEMS_PER_PAGE));
        int totalCount = getProductService().countAllProduct();
        req.setAttribute("pageCount",getPageCount(totalCount, Constants.ITEMS_PER_PAGE));
        RoutingUtils.forwardToPage("products.jsp", req, resp);
    }
}
