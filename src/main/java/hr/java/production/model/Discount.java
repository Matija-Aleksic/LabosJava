package hr.java.production.model;

public record Discount(double discountAmount) {

    /**
     * Gets discount amount.
     *
     * @return the discount amount
     */
    public double getDiscountAmount() {
        return discountAmount;
    }

    public String getDiscountAmountString() {
        return String.valueOf(discountAmount);
    }
}
