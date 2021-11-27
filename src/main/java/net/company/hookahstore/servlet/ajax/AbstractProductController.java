package net.company.hookahstore.servlet.ajax;

import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.servlet.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;
import net.company.hookahstore.utils.SessionUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractProductController extends AbstractController {

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ProductForm productForm = createProductForm(req);
        ShoppingCart shoppingCart = getCurrentShoppingCart(req);
        processProductForm(productForm,shoppingCart,req,resp);
        if (!SessionUtils.isCurrentShoppingCartCreated(req)){
            SessionUtils.setCurrentShoppingCart(req,shoppingCart);
        }
        sendResponse(shoppingCart,req,resp);
    }
    protected abstract void processProductForm(ProductForm productForm, ShoppingCart shoppingCart,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    private ShoppingCart getCurrentShoppingCart(HttpServletRequest req){
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
        }
        return shoppingCart;
    }
    protected void sendResponse(ShoppingCart shoppingCart,HttpServletRequest req, HttpServletResponse resp) throws IOException{
        JSONObject cardStatistics = new JSONObject();
        cardStatistics.put("totalCost",shoppingCart.getTotalCost());
        cardStatistics.put("totalCount", shoppingCart.getTotalCount());
        if (req.getAttribute("id")==null){
            cardStatistics.put("subTotal",0);
            cardStatistics.put("count",0);
        } else {
            cardStatistics.put("subTotal",shoppingCart.getProducts().get((Long)(req.getAttribute("id"))).getSubTotal());
            cardStatistics.put("count",shoppingCart.getProducts().get((Long)(req.getAttribute("id"))).getCount());
        }

        RoutingUtils.sendJSON(cardStatistics,req,resp);
    }
}
