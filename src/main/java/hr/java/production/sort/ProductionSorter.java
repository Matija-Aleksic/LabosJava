package hr.java.production.sort;

import hr.java.production.model.Item;

import java.util.Comparator;

/**
 * The type Production sorter.
 */
public class ProductionSorter implements Comparator<Item> {
    private final boolean ascending;

    /**
     * Instantiates a new Production sorter.
     *
     * @param ascending the ascending
     */
    public ProductionSorter(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public int compare(Item item1, Item item2) {
        if (ascending) {
            return Double.compare(item1.getSellingPrice().doubleValue(), item2.getSellingPrice().doubleValue());
        } else {
            return Double.compare(item2.getSellingPrice().doubleValue(), item1.getSellingPrice().doubleValue());
        }
    }
}
