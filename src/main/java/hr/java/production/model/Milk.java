package hr.java.production.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The type Milk.
 */
public class Milk extends Item implements Edible {
    private final int caloriesPerKilogram = 42;
    private Integer weight;

    /**
     * Instantiates a new Milk.
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
    public Milk(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Integer weight) {
        super(name, category, width, height, length, productionCost, sellingPrice);
        this.weight = weight;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int calculateKilocalories() {
        return caloriesPerKilogram * weight;
    }

    @Override
    public BigDecimal calculatePrice(double weight) {
        return BigDecimal.valueOf(weight).multiply(super.getSellingPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Milk milk)) return false;
        if (!super.equals(o)) return false;
        return caloriesPerKilogram == milk.caloriesPerKilogram && Objects.equals(getWeight(), milk.getWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), caloriesPerKilogram, getWeight());
    }
}
