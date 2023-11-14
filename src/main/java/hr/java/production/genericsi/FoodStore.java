package hr.java.production.genericsi;

import hr.java.production.model.Edible;
import hr.java.production.model.Item;
import hr.java.production.model.Store;

import java.util.ArrayList;
import java.util.List;

public class FoodStore<T extends Edible> extends Store {

    private List<T> edibleItems;

    public FoodStore(String name, String webAddress, ArrayList<Item> items) {
        super(name,webAddress,items);
        this.edibleItems = new ArrayList<>();
    }

    public void addItem(T item) {
        edibleItems.add(item);
    }

    public List<T> getEdibleItems() {
        return edibleItems;
    }
}
