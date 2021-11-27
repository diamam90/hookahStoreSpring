package net.company.hookahstore.listener;

import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.service.OrderService;
import net.company.hookahstore.service.ServiceManager;
import net.company.hookahstore.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AutoRestoreShoppingCartFilter")
public class AutoRestoreShoppingCartFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AutoRestoreShoppingCartFilter.class);
    private static final String SHOPPING_CART_DESEREALIZATION_DONE = "SHOPPING_CART_DESEREALIZATION_DONE";
    private OrderService orderService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        orderService = ServiceManager.getInstance(filterConfig.getServletContext()).getOrderService();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest req= ((HttpServletRequest) request);
            if (req.getSession().getAttribute("SHOPPING_CART_DESEREALIZATION_DONE")==null){
                if (!SessionUtils.isCurrentShoppingCartCreated(req)){
                        Cookie cookie = SessionUtils.findShoppingCartCookie(req);
                    if (cookie!=null){
                        ShoppingCart shoppingCart = orderService.deserializeShoppingCart(cookie.getValue());
                        if (shoppingCart!=null){
                            SessionUtils.setCurrentShoppingCart(req,shoppingCart);
                        }
                    }

                }
                req.getSession().setAttribute(SHOPPING_CART_DESEREALIZATION_DONE,Boolean.TRUE);
            }
            chain.doFilter(req,response);
    }
}
