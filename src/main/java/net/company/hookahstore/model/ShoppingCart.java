package net.company.hookahstore.model;


import net.company.hookahstore.Constants;
import net.company.hookahstore.entity.Product;
import net.company.hookahstore.exception.ValidationException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {
    private int totalCount = 0;
    private Map<Long, ShoppingCartItem> products = new LinkedHashMap<>();
    private BigDecimal totalCost = BigDecimal.ZERO;

    public void addProduct(Product product, int count) {
        validateShoppingCart(product.getId());
        ShoppingCartItem item = products.get(product.getId());
        if (item==null){
            validateCount(count);
            products.put(product.getId(),new ShoppingCartItem(product,count));
        } else {
            validateCount(item.getCount()+count);
            item.setCount(item.getCount()+count);
        }
        refreshStatistic();
    }
    public void removeProduct(Long idProduct,int count){
        ShoppingCartItem item = products.get(idProduct);
        if (item!= null){
            if (count>=item.getCount()){
                products.remove(idProduct);
            }else{
                item.setCount(item.getCount()-count);
            }
        }else {
            throw new IllegalArgumentException();
        }
        refreshStatistic();
    }
    public void validateShoppingCart(long idProduct) {
        if (!products.containsKey(idProduct) && products.size() == Constants.MAX_PRODUCTS_PER_SHOPPING_CART) {
            throw new ValidationException("Limit products in your shopping cart: " + products.size());
        }
    }

    public void validateCount(int count) {
        if (count > Constants.MAX_PRODUCT_COUNT) {
            throw new ValidationException("Count more than: " + Constants.MAX_PRODUCT_COUNT + " count = " + count);
        }
    }
    public void refreshStatistic(){
        totalCount=0;
        totalCost=BigDecimal.ZERO;
        for (ShoppingCartItem item: getItems()){
            totalCount+=item.getCount();
            totalCost=totalCost.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCount())));
            item.refreshSubTotal();
         }
    }

    public Collection<ShoppingCartItem> getItems(){
        return products.values();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Map<Long, ShoppingCartItem> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, ShoppingCartItem> products) {
        this.products = products;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "totalCount=" + totalCount +
                ", totalCost=" + totalCost +
                ", products=" + products +
                '}';
    }
}
