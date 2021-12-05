package net.company.hookahstore.servlet.page;

import net.company.hookahstore.Constants;
import net.company.hookahstore.servlet.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/categories")
public class CategoryController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int totalCount = getProductService().countProductByAside(req);
        req.setAttribute("products",getProductService().listProductByCategory(req,getPage(req), Constants.ITEMS_PER_PAGE));
        req.setAttribute("pageCount",getPageCount(totalCount,Constants.ITEMS_PER_PAGE));
        RoutingUtils.forwardToPage("products.jsp",req,resp);
    }
}
