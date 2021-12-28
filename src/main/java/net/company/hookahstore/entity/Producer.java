package net.company.hookahstore.entity;

import javax.persistence.*;

@Entity
@Table(name="producer")
public class Producer extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private int productCount;

    public Producer() {
    }

    public Producer(String name, int productCount) {
        this.name = name;
        this.productCount = productCount;
    }

    public Long getId() {
        return getId();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getName() {
        return name;
    }

    public int getProductCount() {
        return productCount;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "name='" + name + '\'' +
                ", productCount=" + productCount +
                '}';
    }
}
