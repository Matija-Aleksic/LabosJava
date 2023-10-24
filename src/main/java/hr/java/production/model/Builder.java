package hr.java.production.model;

import java.math.BigDecimal;

public class Builder {
    private String name;
    private Category category;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal length;
    private BigDecimal productionCost;
    private BigDecimal sellingPrice;
    private Integer weight;
    private String description;
    private Address address;
    private Item[] items;
    private Discount discount;
    private int warrantyDurationInMonths;
    private String webAddress;

    public Builder setName(String name) {
        this.name = name;
        return this;
    }

    public Builder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Builder setWidth(BigDecimal width) {
        this.width = width;
        return this;
    }

    public Builder setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public Builder setLength(BigDecimal length) {
        this.length = length;
        return this;
    }

    public Builder setProductionCost(BigDecimal productionCost) {
        this.productionCost = productionCost;
        return this;
    }

    public Builder setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
        return this;
    }

    public Builder setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Bread createBread() {
        return new Bread(name, category, width, height, length, productionCost, sellingPrice, weight);
    }

    public Builder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Category createCategory() {
        return new Category(name);
    }

    public Builder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Builder setItems(Item[] items) {
        this.items = items;
        return this;
    }

    public Factory createFactory() {
        return new Factory(name, address, items);
    }

    public Builder setDiscount(Discount discount) {
        this.discount = discount;
        return this;
    }

    public Item createItem() {
        return new Item(name, category, width, height, length, productionCost, sellingPrice);
    }

    public Builder setWarrantyDurationInMonths(int warrantyDurationInMonths) {
        this.warrantyDurationInMonths = warrantyDurationInMonths;
        return this;
    }

    public Laptop createLaptop() {
        return new Laptop(name, category, width, height, length, productionCost, sellingPrice, warrantyDurationInMonths);
    }

    public Milk createMilk() {
        return new Milk(name, category, width, height, length, productionCost, sellingPrice, weight);
    }

    public NamedEntity createNamedEntity() {
        return new NamedEntity(name);
    }

    public Builder setWebAddress(String webAddress) {
        this.webAddress = webAddress;
        return this;
    }

    public Store createStore() {
        return new Store(name, webAddress, items);
    }
}
