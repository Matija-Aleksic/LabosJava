package hr.java.production.model;

import java.math.BigDecimal;

/**
 * The type Bread.
 */
public class Bread extends Item implements Edible {
    private int caloriesPerKilogram = 265;
    private int weight;

    /**
     * Instantiates a new Bread.
     *
     * @param name           the name
     * @param category       the category
     * @param width          the width
     * @param height         the height
     * @param length         the length
     * @param productionCost the production cost
     * @param sellingPrice   the selling price
     * @param weight         the weight
     */
    public Bread(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Integer weight) {
        super(name, category, width, height, length, productionCost, sellingPrice);
        this.weight = weight;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
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
