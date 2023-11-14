package hr.java.production.model;

/**
 * The type Discount.
 */
public record Discount() {
    /**
     * The constant discountAmount.
     */
    static double discountAmount;

    /**
     * Gets discount amount.
     *
     * @return the discount amount
     */
    public double getDiscountAmount() {
        return discountAmount;
    }


}
