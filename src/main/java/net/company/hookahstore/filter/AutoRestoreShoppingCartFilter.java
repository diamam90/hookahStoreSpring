package net.company.hookahstore.filter;
/*
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.service.OrderService;
import net.company.hookahstore.service.ServiceManager;
import net.company.hookahstore.utils.SessionUtils;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AutoRestoreShoppingCartFilter")
public class AutoRestoreShoppingCartFilter extends AbstractFilter {
    private static final String SHOPPING_CART_DESEREALIZATION_DONE = "SHOPPING_CART_DESEREALIZATION_DONE";
    private OrderService orderService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        orderService = ServiceManager.getInstance(filterConfig.getServletContext()).getOrderService();
    }

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req.getSession().getAttribute(SHOPPING_CART_DESEREALIZATION_DONE) == null) {
            if (!SessionUtils.isCurrentShoppingCartCreated(req)) {
                Cookie cookie = SessionUtils.findShoppingCartCookie(req);
                if (cookie != null) {
                    ShoppingCart shoppingCart = orderService.deserializeShoppingCart(cookie.getValue());
                    if (shoppingCart != null) {
                        SessionUtils.setCurrentShoppingCart(req, shoppingCart);
                    }
                }
            }
            req.getSession().setAttribute(SHOPPING_CART_DESEREALIZATION_DONE,Boolean.TRUE);
        }
        req.setAttribute("lastUrl",req.getQueryString()==null? req.getRequestURI():req.getRequestURI()+"?"+req.getQueryString());
        chain.doFilter(req,resp);
    }
}
*/