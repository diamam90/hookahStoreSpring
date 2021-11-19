package net.company.hookahstore.utils;

import net.company.hookahstore.Constants;
import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.model.SocialAccount;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionUtils {
    public static ShoppingCart getCurrentShoppingCart(HttpServletRequest req){
        ShoppingCart shoppingCart = (ShoppingCart)req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART);
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
            setCurrentShoppingCart(req,shoppingCart);
        } return shoppingCart;
    }

    public static void setCurrentShoppingCart(HttpServletRequest req,ShoppingCart shoppingCart){
        req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART,shoppingCart);
    }
    public static boolean isCurrentShoppingCartCreated(HttpServletRequest req){
        return req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART)!=null;
    }
    public static void clearCurrentShoppingCart(HttpServletRequest req){
        req.getSession().removeAttribute(Constants.CURRENT_SHOPPING_CART);
    }
    public static Cookie findShoppingCartCookie(HttpServletRequest req){
       return WebUtils.findCookie(req,Constants.Cookie.SHOPPING_CART.getName());
    }
    public static void updateCurrentShoppingCartCookie(String cookieValue, HttpServletRequest req, HttpServletResponse resp){
        WebUtils.setCookie(Constants.Cookie.SHOPPING_CART.getName(),cookieValue,Constants.Cookie.SHOPPING_CART.getTtl(),resp);
    }
    public static boolean isCurrentAccountCreated (HttpServletRequest req){
        return req.getSession().getAttribute(Constants.CURRENT_ACCOUNT)!=null;
    }
    public static void setCurrentAccount(HttpServletRequest req, CurrentAccount currentAccount){
        req.getSession().setAttribute(Constants.CURRENT_ACCOUNT,currentAccount);
    }
    public static CurrentAccount getCurrentAccount(HttpServletRequest req){
        return (CurrentAccount )req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
    }
    private SessionUtils(){}
}
