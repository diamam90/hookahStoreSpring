package net.company.hookahstore.entity;

import java.math.BigDecimal;

public class Product extends AbstractEntity<Long> {
    private String name;
    private String category;
    private String producer;
    private String imageLinkLarge;
    private String description;
    private BigDecimal price;
    private int count;
    private String imageLinkSmall;

    public Product(String name, String description, String category, String producer, BigDecimal price, int count, String imageLinkLarge, String imageLinkSmall) {
        this.name = name;
        this.imageLinkLarge = imageLinkLarge;
        this.description = description;
        this.category = category;
        this.producer = producer;
        this.price = price;
        this.count = count;
        this.imageLinkSmall = imageLinkSmall;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getProducer() {
        return producer;
    }


    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getImageLinkLarge() {
        return imageLinkLarge;
    }

    public void setImageLinkLarge(String imageLinkLarge) {
        this.imageLinkLarge = imageLinkLarge;
    }

    public String getImageLinkSmall() {
        return imageLinkSmall;
    }

    public void setImageLinkSmall(String imageLinkSmall) {
        this.imageLinkSmall = imageLinkSmall;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product() {
    }
}
