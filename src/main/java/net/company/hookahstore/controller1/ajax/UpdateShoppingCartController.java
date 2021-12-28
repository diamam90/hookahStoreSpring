package net.company.hookahstore.controller1.ajax;
/*
import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.utils.SessionUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/json/shopping-cart/update")
public class UpdateShoppingCartController extends AbstractProductController{
    @Override
    protected void processProductForm(ProductForm productForm, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getOrderService().updateShoppingCart(productForm,shoppingCart);
        String cookie = getOrderService().selializeShoppingCart(shoppingCart);
        SessionUtils.updateCurrentShoppingCartCookie(cookie,req,resp);
        if (shoppingCart.getProducts().get(productForm.getIdProduct())!=null){
            req.setAttribute("id",productForm.getIdProduct());
        }
    }
}
*/