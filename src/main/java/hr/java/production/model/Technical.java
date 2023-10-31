package hr.java.production.model;

/**
 * The interface Technical.
 */
public sealed interface Technical permits Laptop {
    /**
     * Gets warranty duration in months.
     *
     * @return warranty duration in months
     */
    int getWarrantyDurationInMonths();
}