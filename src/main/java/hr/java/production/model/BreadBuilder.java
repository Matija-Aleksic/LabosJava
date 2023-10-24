package hr.java.production.model;

import java.math.BigDecimal;

public class BreadBuilder {
    private String name;
    private Category category;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal length;
    private BigDecimal productionCost;
    private BigDecimal sellingPrice;
    private Integer weight;

    public BreadBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BreadBuilder setCategory(Category category) {
        this.category = category;
        return this;
    }

    public BreadBuilder setWidth(BigDecimal width) {
        this.width = width;
        return this;
    }

    public BreadBuilder setHeight(BigDecimal height) {
        this.height = height;
        return this;
    }

    public BreadBuilder setLength(BigDecimal length) {
        this.length = length;
        return this;
    }

    public BreadBuilder setProductionCost(BigDecimal productionCost) {
        this.productionCost = productionCost;
        return this;
    }

    public BreadBuilder setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
        return this;
    }

    public BreadBuilder setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public Bread createBread() {
        return new Builder().setName(name).setCategory(category).setWidth(width).setHeight(height).setLength(length).setProductionCost(productionCost).setSellingPrice(sellingPrice).setWeight(weight).createBread();
    }
}