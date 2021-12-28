package net.company.hookahstore.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name="product")
@Entity
public class Product extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name="id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name="id_producer")
    private Producer producer;

    @Column(name="image_link_large")
    private String imageLinkLarge;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @Column
    private int count;
    @Column(name="image_link_small")
    private String imageLinkSmall;




    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
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
