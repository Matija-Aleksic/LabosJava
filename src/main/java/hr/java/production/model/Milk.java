package hr.java.production.model;

import java.math.BigDecimal;

public class Milk extends Item implements Edible{
    private int caloriesPerKilogram = 42;
    private Integer weight;


    public Milk(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Integer weight) {
        super(name, category, width, height, length, productionCost, sellingPrice);
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int calculateKilocalories() {
        return caloriesPerKilogram*weight;
    }

    @Override
    public BigDecimal calculatePrice(double weight) {
        return BigDecimal.valueOf(weight).multiply(super.getSellingPrice()) ;
    }
}
