package net.company.hookahstore.servlet.page;

import net.company.hookahstore.entity.Product;
import net.company.hookahstore.service.ServiceManager;
import net.company.hookahstore.utils.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceManager serviceManager = ServiceManager.getInstance(req.getServletContext());
        String query = req.getParameter("query");
        String category = req.getParameter("cat");
        if (category!= null){
            List<Product> products = serviceManager.getProductService().listProductBySearch(req.getParameter("cat"),req.getParameter("query"));
            int totalCount = serviceManager.getProductService().countProductBySearch(req.getParameter("cat"),req.getParameter("query"));
            req.setAttribute("products",products);
            req.setAttribute("totalCount",totalCount);
            RoutingUtils.forwardToPage("search.jsp",req,resp);
        } else {
            RoutingUtils.forwardToPage("products.jsp",req,resp);
        }

    }
}
