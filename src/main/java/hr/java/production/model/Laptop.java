package hr.java.production.model;

import java.math.BigDecimal;

/**
 * The type Laptop.
 */
public final class Laptop extends Item implements Technical {
    private final int warrantyDurationInMonths;

    /**
     * Instantiates a new Laptop.
     *
     * @param name                     the name
     * @param category                 the category
     * @param width                    the width
     * @param height                   the height
     * @param length                   the length
     * @param productionCost           the production cost
     * @param sellingPrice             the selling price
     * @param warrantyDurationInMonths the warranty duration in months
     */
    public Laptop(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, int warrantyDurationInMonths) {
        super(name, category, width, height, length, productionCost, sellingPrice);
        this.warrantyDurationInMonths = warrantyDurationInMonths;
    }

    /**
     * Instantiates a new Laptop.
     *
     * @param name                     the name
     * @param category                 the category
     * @param width                    the width
     * @param height                   the height
     * @param length                   the length
     * @param productionCost           the production cost
     * @param sellingPrice             the selling price
     * @param discount                 the discount
     * @param warrantyDurationInMonths the warranty duration in months
     */
    public Laptop(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Discount discount, int warrantyDurationInMonths) {
        super(name, category, width, height, length, productionCost, sellingPrice, discount);
        this.warrantyDurationInMonths = warrantyDurationInMonths;
    }

    @Override
    public int getWarrantyDurationInMonths() {
        return warrantyDurationInMonths;
    }

}