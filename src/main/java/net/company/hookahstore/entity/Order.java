package net.company.hookahstore.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="order")
public class Order{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column(name="id_account")
    private Long idAccount;
    @Column
    private Timestamp created;
    @Column
    @OneToMany
    private List<OrderItem> itemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order(){}
    public Order(Long idAccount,Timestamp created, List<OrderItem> itemList){
        this.idAccount = idAccount;
        this.created=created;
        this.itemList=itemList;
    }
    public Timestamp getCreated(){
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }


    public List<OrderItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", idAccount=" + idAccount +
                ", created=" + created +
                ", itemList=" + itemList +
                '}';
    }


    public BigDecimal getTotalCost(){
        BigDecimal total = BigDecimal.ZERO;
        if (itemList!=null){
            for (OrderItem item: itemList){
                total=total.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCount())));
            }
        }
        return total;
    }
    public int getTotalCount(){
        return itemList.size();
    }
}
