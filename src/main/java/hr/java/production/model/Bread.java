package hr.java.production.model;

import java.math.BigDecimal;

public class Bread extends Item implements Edible {
    private int caloriesPerKilogram = 265;
    private int weight;


    public Bread(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Integer weight) {
        super(name, category, width, height, length, productionCost, sellingPrice);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
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
