package net.company.hookahstore.DTO;


import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.model.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class UpdateProductFromShoppingCartDTO {
    private Integer totalCount;
    private BigDecimal totalCost;
    private Map<Long,BigDecimal> subTotalMap;
    private Map<Long,Integer> countMap;

    public UpdateProductFromShoppingCartDTO(ShoppingCart shoppingCart){
        subTotalMap=new HashMap<>();
        countMap = new HashMap<>();
        this.totalCount =shoppingCart.getTotalCount();
        this.totalCost=shoppingCart.getTotalCost();
        for (ShoppingCartItem item: shoppingCart.getItems()){
            subTotalMap.put(item.getProduct().getId(),item.getSubTotal());
            countMap.put(item.getProduct().getId(),item.getCount());
        }
    }

    public Map<Long, Integer> getCountMap() {
        return countMap;
    }

    public void setCountMap(Map<Long, Integer> countMap) {
        this.countMap = countMap;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Map<Long, BigDecimal> getSubTotalMap() {
        return subTotalMap;
    }

    public void setSubTotalMap(Map<Long, BigDecimal> subTotalMap) {
        this.subTotalMap = subTotalMap;
    }

    public UpdateProductFromShoppingCartDTO() {
    }

    @Override
    public String toString() {
        return "UpdateProductFromShoppingCartDTO{" +
                "totalCount=" + totalCount +
                ", totalCost=" + totalCost +
                ", subTotalMap=" + subTotalMap +
                '}';
    }
}
