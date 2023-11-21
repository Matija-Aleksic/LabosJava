package hr.java.production.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import static hr.java.production.model.Item.findItemById;

/**
 * The type Store.
 */
public class Store extends NamedEntity implements Serializable {

    private String webAddress;
    private ArrayList<Item> items;


    public Store(Long id, String name, String webAddress, ArrayList<Item> items) {
        super(id, name);
        this.webAddress = webAddress;
        this.items = items;
    }

    public static ArrayList<Store> loadStoresFromFile(String fileName) {
        ArrayList<Store> stores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Long id = Long.parseLong(line.trim()); // Prvi redak je identifikator dućana
                String name = reader.readLine().trim(); // Drugi redak je ime dućana
                String webAddress = reader.readLine().trim(); // Treći redak je web adresa dućana

                // Čitanje identifikatora artikala
                String[] itemIds = reader.readLine().split(",");
                ArrayList<Item> itemList = new ArrayList<>();
                for (String itemId : itemIds) {
                    Long itemIdLong = Long.parseLong(itemId.trim());
                    Item item = findItemById(itemIdLong); // Implementirati logiku za pronalazak artikla po ID-u
                    if (item != null) {
                        itemList.add(item);
                    }
                }

                stores.add(new Store(id, name, webAddress, itemList));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stores;
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    /**
     * Gets web address.
     *
     * @return the web address
     */
    public String getWebAddress() {
        return webAddress;
    }

    /**
     * Sets web address.
     *
     * @param webAddress the web address
     */
    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    /**
     * Get items item [ ].
     *
     * @return the item [ ]
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getNumberOfItems() {
        return items.size();
    }


}
