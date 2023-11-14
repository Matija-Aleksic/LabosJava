package hr.java.production.sort;

import hr.java.production.model.Item;

import java.util.Comparator;

public class VolumeComparator implements Comparator<Item> {
    private boolean ascending = Boolean.TRUE;

    /**
     * Instantiates a new Production sorter.
     *
     * @param ascending the ascending
     */
    public VolumeComparator(boolean ascending) {
        this.ascending = ascending;
    }

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


