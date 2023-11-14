package hr.java.production.model;

/**
 * The type Discount.
 */
public record Discount() {
    /**
     * The constant discountAmount.
     */
    static double discountAmount;

    public double getDiscountAmount() {
        return discountAmount;
    }


}
