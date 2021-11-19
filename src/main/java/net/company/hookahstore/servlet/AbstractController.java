package net.company.hookahstore.servlet;

import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.service.OrderService;
import net.company.hookahstore.service.ProductService;
import net.company.hookahstore.service.ServiceManager;
import net.company.hookahstore.service.SocialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class AbstractController extends HttpServlet {
    private ProductService productService;
    private OrderService orderService;
    private SocialService socialService;

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void init()  {
        productService = ServiceManager.getInstance(getServletContext()).getProductService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
        socialService = ServiceManager.getInstance(getServletContext()).getSocialService();
    }
    public ProductService getProductService(){ return productService;}
    public OrderService getOrderService(){return orderService;}
    public SocialService getSocialService() {return socialService;}

    public final int getPage(HttpServletRequest req){
        try {
            return Integer.parseInt(req.getParameter("page"));
        }catch (NumberFormatException e){
            return 1;
        }
    }
    public final int getPageCount(int totalCount, int itemsPerPage){
        int res = totalCount/itemsPerPage;
        if (res*itemsPerPage!= totalCount){
            res++;
        }
        return res;
    }
    public final ProductForm createProductForm(HttpServletRequest req){
        return new ProductForm(
                Long.parseLong(req.getParameter("idProduct")),
                Integer.parseInt(req.getParameter("count")));
    }

}
