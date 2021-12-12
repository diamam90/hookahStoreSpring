package net.company.hookahstore.entity;

public class Producer extends AbstractEntity<Long> {
    private String name;
    private int productCount;

    public Producer() {
    }

    public Producer(String name, int productCount) {
        this.name = name;
        this.productCount = productCount;
    }

    @Override
    public Long getId() {
        return super.getId();
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
