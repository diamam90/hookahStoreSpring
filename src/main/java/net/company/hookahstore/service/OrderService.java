package net.company.hookahstore.service;

import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.model.ShoppingCart;

public interface OrderService {
    void addProductToShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);
    void removeProductFromShoppingCart(ProductForm productForm, ShoppingCart shoppingCart);
    String selializeShoppingCart(ShoppingCart shoppingCart);
    ShoppingCart deserializeShoppingCart(String cookieValue);
}
