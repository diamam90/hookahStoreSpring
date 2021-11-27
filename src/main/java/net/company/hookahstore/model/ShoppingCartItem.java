package net.company.hookahstore.model;

import net.company.hookahstore.entity.Product;

import java.math.BigDecimal;

public class ShoppingCartItem {
    private Product product;
    private int count;
    private BigDecimal subTotal = BigDecimal.ZERO;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "product=" + product +
                ", count=" + count +
                ", subTotal=" + subTotal +
                '}';
    }
    public void refreshSubTotal(){
        setSubTotal(product.getPrice().multiply(BigDecimal.valueOf(count)));
    }

    public ShoppingCartItem(){}
    public ShoppingCartItem(Product product, int count){
        this.product=product;
        this.count=count;
        this.subTotal=product.getPrice().multiply(BigDecimal.valueOf(count));
    }
}
