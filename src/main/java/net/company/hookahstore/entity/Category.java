package net.company.hookahstore.entity;

import java.util.List;

public class Category extends AbstractEntity<Long> {
    private String name;
    private int productCount;
    private String url;
    private String ruName;

    @Override
    public Long getId() {
        return super.getId();
    }

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
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", productCount=" + productCount +
                ", url='" + url + '\'' +
                ", ruName='" + ruName + '\'' +
                '}';
    }
}
