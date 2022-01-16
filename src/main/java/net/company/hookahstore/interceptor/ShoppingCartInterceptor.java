package net.company.hookahstore.interceptor;

import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.service.OrderService;
import net.company.hookahstore.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShoppingCartInterceptor implements HandlerInterceptor {
    private static final String SHOPPING_CART_DESERIALIZATION_DONE = "SHOPPING_CART_DESERIALIZATION_DONE";

    @Autowired
    OrderService orderService;

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        if (req.getSession().getAttribute(SHOPPING_CART_DESERIALIZATION_DONE) == null) {
            if (!SessionUtils.isCurrentShoppingCartCreated(req)) {
                Cookie cookie = SessionUtils.findShoppingCartCookie(req);
                if (cookie != null) {
                    ShoppingCart shoppingCart = orderService.deserializeShoppingCart(cookie.getValue());
                    SessionUtils.setCurrentShoppingCart(req,shoppingCart);
                }
            }
            req.getSession().setAttribute(SHOPPING_CART_DESERIALIZATION_DONE, Boolean.TRUE);
        }
         return HandlerInterceptor.super.preHandle(req, resp, handler);
    }
}
