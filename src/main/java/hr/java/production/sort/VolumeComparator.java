package hr.java.production.sort;

import hr.java.production.model.Item;

import java.util.Comparator;

/**
 * The type Volume comparator.
 */
public class VolumeComparator implements Comparator<Item> {
    private boolean ascending = Boolean.TRUE;

    /**
     * Instantiates a new Volume comparator.
     *
     * @param ascending the ascending
     */
    public VolumeComparator(boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * Instantiates a new Volume comparator.
     */
    public VolumeComparator() {
    }


    @Override
    public int compare(Item item1, Item item2) {
        if (ascending) {
            return Double.compare(item1.getVolume().doubleValue(), item2.getVolume().doubleValue());
        } else {
            return Double.compare(item2.getVolume().doubleValue(), item1.getVolume().doubleValue());
        }
    }
}


