package net.company.hookahstore.controller1.page;
/*
import net.company.hookahstore.Constants;
import net.company.hookahstore.controller.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
//@WebServlet("/allproducts")
public class AllProductController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", getProductService().listAllProduct(getPage(req), getItemsPerPage(req)));
        int totalCount = getProductService().countAllProduct();
        req.setAttribute("pageCount",getPageCount(totalCount, Constants.ITEMS_PER_PAGE));
        RoutingUtils.forwardToPage("products.jsp", req, resp);
    }
}
*/