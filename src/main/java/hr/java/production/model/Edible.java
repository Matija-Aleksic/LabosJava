package hr.java.production.model;

import java.math.BigDecimal;

/**
 * The interface Edible.
 */
public interface Edible {
    /**
     * Calculate kilocalories int.
     *
     * @return the int
     */
    int calculateKilocalories();

    /**
     * Calculate price big decimal.
     *
     * @param weight the weight
     * @return big decimal
     */
    BigDecimal calculatePrice(double weight);
}
