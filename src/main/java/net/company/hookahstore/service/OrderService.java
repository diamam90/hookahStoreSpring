package net.company.hookahstore.service;

import net.company.hookahstore.entity.Order;
import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.model.ShoppingCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface OrderService {
    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);
    void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);
    void updateShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);
    String serializeShoppingCart(ShoppingCart shoppingCart);
    ShoppingCart deserializeShoppingCart(String cookieValue);
    long makeOrder(ShoppingCart shoppingCart, CurrentAccount currentAccount);
    Order findOrderById(long id,CurrentAccount currentAccount);
    Page<Order> listMyOrders(CurrentAccount currentAccount, Pageable pageable);
    int countMyOrders(CurrentAccount currentAccount);
}
