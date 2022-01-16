package net.company.hookahstore.entity;


import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column(name="product_count")
    private int productCount;
    @Column
    private String url;
    @Column(name="ru_name")
    private String ruName;

    public Category() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuName() {
        return ruName;
    }

    public void setRuName(String ruName) {
        this.ruName = ruName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category(String name, int productCount, String url, String ruName) {
        this.name = name;
        this.productCount = productCount;
        this.url = url;
        this.ruName = ruName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProductCount() {
        return productCount;
    }

    public String getUrl() {
        return url;
    }

    public Category(Long id, String name, int productCount, String url, String ruName) {
        this.id = id;
        this.name = name;
        this.productCount = productCount;
        this.url = url;
        this.ruName = ruName;
    }
}
