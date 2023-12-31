package hr.java.production.model;

import hr.java.production.database.Database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static hr.java.production.model.Address.Builder.findAddressById;
import static hr.java.production.model.Item.findItemById;

/**
 * The type Factory.
 */
public class Factory extends NamedEntity implements Serializable {


    private Address address;
    private Item[] items;


    public Factory(Long id, String name, Address address, Item[] items) {
        super(id, name);
        this.address = address;
        this.items = items;
    }

    public Factory(Long id, String name, Address address) {
        super(id, name);
        this.address = address;
    }

    public static ArrayList<Factory> loadFactoriesFromFile(String fileName) {
        ArrayList<Factory> factories = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Long id = Long.parseLong(line.trim());
                String name = reader.readLine().trim();


                Long addressId = Long.parseLong(reader.readLine().trim());
                Address address = findAddressById(addressId);


                String[] itemIds = reader.readLine().split(",");
                ArrayList<Item> itemList = new ArrayList<>();
                for (String itemId : itemIds) {
                    Long itemIdLong = Long.parseLong(itemId.trim());
                    Item item = findItemById(itemIdLong);
                    if (item != null) {
                        itemList.add(item);
                    }
                }

                factories.add(new Factory(id, name, address, itemList.toArray(new Item[0])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return factories;
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
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
            ArrayList<Item> databaseItems = (ArrayList<Item>) database.getItemsForFactory(this.getId());
            return databaseItems;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //TODO dodat logiku kad se naprave

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(Item[] items) {
        this.items = items;
    }



    public long getAddressId() {
        return address.getId();
    }
}
