package net.company.hookahstore.entity;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="order_item")
public class OrderItem{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="id_order")
    private Long orderId;
    @JoinColumn(name="id_product")
    @ManyToOne
    private Product product;
    @Column
    private int count;

    public OrderItem(Long orderId,Product product,int count){
        this.orderId= orderId;
        this.product=product;
        this.count=count;
    }
    public OrderItem(){}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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

    public BigDecimal getSubTotal(){
        return product.getPrice().multiply(BigDecimal.valueOf(count));
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + orderId +
                ", product=" + product +
                ", count=" + count +
                '}';
    }
}
