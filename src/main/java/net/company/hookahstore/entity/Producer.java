package net.company.hookahstore.entity;

import javax.persistence.*;

@Entity
@Table(name="producer")
public class Producer{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column(name="product_count")
    private int productCount;

    public Producer() {
    }

    public Producer(String name, int productCount) {
        this.name = name;
        this.productCount = productCount;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }
}
