package hr.java.production.model;

import java.math.BigDecimal;

public final class Laptop extends Item implements Technical {
    private final int warrantyDurationInMonths;

    public Laptop(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, int warrantyDurationInMonths) {
        super(name, category, width, height, length, productionCost, sellingPrice);
        this.warrantyDurationInMonths = warrantyDurationInMonths;
    }

    public Laptop(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Discount discount, int warrantyDurationInMonths) {
        super(name, category, width, height, length, productionCost, sellingPrice, discount);
        this.warrantyDurationInMonths = warrantyDurationInMonths;
    }

    @Override
    public int getWarrantyDurationInMonths() {
        return warrantyDurationInMonths;
    }

}