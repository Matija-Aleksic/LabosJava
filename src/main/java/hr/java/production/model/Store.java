package hr.java.production.model;

import hr.java.production.database.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
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

    public Store(Long id, String name, String webAddress) {
        super(id, name);
        this.webAddress = webAddress;
    }

    public static ArrayList<Store> loadStoresFromFile(String fileName) {
        ArrayList<Store> stores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Long id = Long.parseLong(line.trim());
                String name = reader.readLine().trim();
                String webAddress = reader.readLine().trim();

                // Čitanje identifikatora artikala
                String[] itemIds = reader.readLine().split(",");
                ArrayList<Item> itemList = new ArrayList<>();
                for (String itemId : itemIds) {
                    Long itemIdLong = Long.parseLong(itemId.trim());
                    Item item = findItemById(itemIdLong);
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
        try {
            Database database = new Database();
            database.openConnection();
            ArrayList<Item> databaseItems = (ArrayList<Item>) database.getItemsForStore(this.getId());
            return databaseItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
