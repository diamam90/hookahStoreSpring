package net.company.hookahstore.entity;

import java.sql.Timestamp;
import java.util.List;

public class Order extends AbstractEntity<Long>{
    private Timestamp created;
    private Timestamp updated;
    private List<OrderItem> itemList;

    public Order(){}
    public Order(Timestamp created, List<OrderItem> itemList){
        this.created=created;
        this.itemList=itemList;
    }
    public Timestamp getCreated(){
        return created;
    }
    public Timestamp getUpdated(){
        return updated;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "created=" + created +
                ", updated=" + updated +
                ", itemList=" + itemList +
                '}';
    }
}
