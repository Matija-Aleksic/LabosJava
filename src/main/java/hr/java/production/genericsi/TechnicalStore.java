package hr.java.production.genericsi;

import hr.java.production.model.Item;
import hr.java.production.model.Store;
import hr.java.production.model.Technical;

import java.util.ArrayList;
import java.util.List;

public class TechnicalStore<T extends Technical> extends Store {

    private ArrayList<T> technicalItems;

    public TechnicalStore(String name, String webAddress, ArrayList<Item> items) {
        super(name,webAddress,items);
        this.technicalItems = new ArrayList<>();
    }

    public void addItem(T item) {
        technicalItems.add(item);
    }

    public ArrayList<T> getTechnicalItems() {
        return technicalItems;
    }
}
