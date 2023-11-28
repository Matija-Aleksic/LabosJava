package hr.java.production.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

    private static Address findAddressById(Long addressId) {
        return null;
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
        ArrayList<Item> items1 = new ArrayList<>();

        return items1;
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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Factory factory)) return false;
//        if (!super.equals(o)) return false;
//        return Objects.equals(getAddress(), factory.getAddress()) && Arrays.equals(getItems(), factory.getItems());
//    }

//    @Override
//    public int hashCode() {
//        int result = Objects.hash(super.hashCode(), getAddress());
//        result = 31 * result + Arrays.hashCode(getItems());
//        return result;
//    }
}
