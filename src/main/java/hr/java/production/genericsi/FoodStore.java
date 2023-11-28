package hr.java.production.genericsi;

import hr.java.production.model.Edible;
import hr.java.production.model.Item;
import hr.java.production.model.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Food store.
 *
 * @param <T> the type parameter
 */
public class FoodStore<T extends Edible> extends Store {

    private final List<T> edibleItems;
    private final Long id;

    /**
     * Instantiates a new Food store.
     *
     * @param name       the name
     * @param webAddress the web address
     * @param items      the items
     * @param id
     */
    public FoodStore(String name, String webAddress, ArrayList<Item> items, Long id) {
        super(id, name, webAddress, items);
        this.id = id;
        this.edibleItems = new ArrayList<>();
    }

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(T item) {
        edibleItems.add(item);
    }

    /**
     * Gets edible items.
     *
     * @return the edible items
     */
    public List<T> getEdibleItems() {
        return edibleItems;
    }
}
