package hr.java.production.genericsi;

import hr.java.production.model.Item;
import hr.java.production.model.Store;
import hr.java.production.model.Technical;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Technical store.
 *
 * @param <T> the type parameter
 */
public class TechnicalStore<T extends Technical> extends Store {

    private final ArrayList<T> technicalItems;

    /**
     * Instantiates a new Technical store.
     *
     * @param name       the name
     * @param webAddress the web address
     * @param items      the items
     */
    public TechnicalStore(String name, String webAddress, ArrayList<Item> items) {
        super(name,webAddress,items);
        this.technicalItems = new ArrayList<>();
    }

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(T item) {
        technicalItems.add(item);
    }

    /**
     * Gets technical items.
     *
     * @return the technical items
     */
    public ArrayList<T> getTechnicalItems() {
        return technicalItems;
    }
}
