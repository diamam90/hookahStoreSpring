package net.company.hookahstore.servlet.ajax;

import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.utils.SessionUtils;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax/json/product/add")
public class addProductController extends AbstractProductController {

    protected void processProductForm(ProductForm productForm, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp){
        getOrderService().addProductToShoppingCart(productForm,shoppingCart);
        String cookie = getOrderService().selializeShoppingCart(shoppingCart);
        SessionUtils.updateCurrentShoppingCartCookie(cookie,req,resp);
    }
}
