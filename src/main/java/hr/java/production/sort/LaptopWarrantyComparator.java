package hr.java.production.sort;

import hr.java.production.model.Laptop;

import java.util.Comparator;

/**
 * The type Laptop warranty comparator.
 */
public class LaptopWarrantyComparator implements Comparator<Laptop> {
    @Override
    public int compare(Laptop laptop1, Laptop laptop2) {
        return Integer.compare(laptop1.getWarrantyDurationInMonths(), laptop2.getWarrantyDurationInMonths());
    }
}