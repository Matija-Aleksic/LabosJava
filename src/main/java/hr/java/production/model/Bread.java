package hr.java.production.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The type Bread.
 */
public class Bread extends Item implements Edible {
    private final int caloriesPerKilogram = 265;
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
    public Bread(Long id, String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Integer weight,Discount discount) {
        super(id, name, category, width, height, length, productionCost, sellingPrice,discount);
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
        return caloriesPerKilogram * weight;
    }

    @Override
    public BigDecimal calculatePrice(double weight) {
        return BigDecimal.valueOf(weight).multiply(super.getSellingPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bread bread)) return false;
        if (!super.equals(o)) return false;
        return caloriesPerKilogram == bread.caloriesPerKilogram && getWeight() == bread.getWeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), caloriesPerKilogram, getWeight());
    }
}
