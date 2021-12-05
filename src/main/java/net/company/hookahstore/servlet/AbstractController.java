package net.company.hookahstore.servlet;

import net.company.hookahstore.Constants;
import net.company.hookahstore.form.LoginForm;
import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.service.AccountService;
import net.company.hookahstore.service.OrderService;
import net.company.hookahstore.service.ProductService;
import net.company.hookahstore.service.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class AbstractController extends HttpServlet {
    private ProductService productService;
    private OrderService orderService;
    private AccountService accountService;

    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void init()  {
        productService = ServiceManager.getInstance(getServletContext()).getProductService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
        accountService = ServiceManager.getInstance(getServletContext()).getAccountService();
    }
    public ProductService getProductService(){ return productService;}
    public OrderService getOrderService(){return orderService;}
    public AccountService getAccountService(){return accountService;}

    public final int getPage(HttpServletRequest req){
        Integer page;
        try {
            page = Integer.parseInt(req.getParameter("page"));
            req.setAttribute("page",page);
            return page;
        }catch (NumberFormatException e){
            page= 1;
            req.setAttribute("page",page);
            return page;
        }
    }
    public final int getItemsPerPage(HttpServletRequest req){
        try {
            return Integer.parseInt(req.getParameter("itemsPerPage"));
        }catch (NumberFormatException e){
            return Constants.ITEMS_PER_PAGE;
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
    public final LoginForm createLoginForm(HttpServletRequest req){
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        return new LoginForm(login,password, name,lastName, email, phone);
    }
}
