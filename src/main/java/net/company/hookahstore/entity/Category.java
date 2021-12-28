package net.company.hookahstore.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category extends AbstractEntity<Long> {


    @Id
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

    public String getName() {
        return name;
    }

    public int getProductCount() {
        return productCount;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", productCount=" + productCount +
                ", url='" + url + '\'' +
                ", ruName='" + ruName + '\'' +
                '}';
    }
}
