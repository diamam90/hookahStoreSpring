package net.company.hookahstore.service.impl;

import net.company.hookahstore.entity.Product;
import net.company.hookahstore.exception.InternalServerErrorException;
import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.jdbc.JDBCUtils;
import net.company.hookahstore.jdbc.ResultSetHandler;
import net.company.hookahstore.jdbc.ResultSetHandlerFactory;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.model.ShoppingCartItem;
import net.company.hookahstore.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class OrderServiceImpl  implements OrderService {
    private final DataSource datasource;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final ResultSetHandler<Product> productResultSetHandler = ResultSetHandlerFactory.getSingleResultSetHandler(
            ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);
    public OrderServiceImpl(DataSource dataSource){
        this.datasource=dataSource;
    }


    public void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart){
            try (Connection c = datasource.getConnection()){
                Product product = JDBCUtils.select(c,"select p.*, c.name as category, p.name as producer from product p, category c, producer pr " +
                        "where p.id_category=c.id and p.id_producer=pr.id and p.id=?",productResultSetHandler,productForm.getIdProduct());
                if (product==null){
                    throw new InternalServerErrorException("product not found by id " +productForm.getIdProduct());
                } else{
                    shoppingCart.addProduct(product,productForm.getCount());
                }
            }catch (SQLException e){
                throw new InternalServerErrorException("can't execute query:" + e.getMessage());
            }
         }

    @Override
    public void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        shoppingCart.removeProduct(productForm.getIdProduct(),productForm.getCount());
    }

    @Override
    public void updateShoppingCart(ProductForm productForm, ShoppingCart shoppingCart) {
        Long id = productForm.getIdProduct();
        Product product=shoppingCart.getProducts().get(id).getProduct();
        int countFromPage = productForm.getCount();
        int countFromCart = shoppingCart.getProducts().get(id).getCount();
        if (countFromCart<countFromPage){
            shoppingCart.addProduct(product,countFromPage-countFromCart);
        } else if(countFromCart>countFromPage){
              shoppingCart.removeProduct(id,countFromCart-countFromPage);
        }
    }

    @Override
    public String selializeShoppingCart(ShoppingCart shoppingCart) {
        StringBuilder cookieStr = new StringBuilder();
        for (ShoppingCartItem item:shoppingCart.getItems()){
            cookieStr.append(item.getProduct().getId()+"-"+item.getCount()+"|");
        }
        return cookieStr.toString();
    }

    @Override
    public ShoppingCart deserializeShoppingCart(String cookieValue) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = cookieValue.split("\\|");
        for (int i = 0; i<items.length;i++){
            String[] data = items[i].split("-");
            Long idProduct;
            Integer count;
            try {
                idProduct = Long.parseLong(data[0]);
                count = Integer.parseInt(data[1]);
                addProductToShoppingCart(new ProductForm(idProduct,count),shoppingCart);
            }catch (RuntimeException e){
                LOGGER.error("can't add product to shopping cart during deserialization: item = " + items,e);
            }
        }
        return shoppingCart;
    }
}
