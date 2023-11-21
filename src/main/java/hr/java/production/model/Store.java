package hr.java.production.model;

import java.util.ArrayList;

/**
 * The type Store.
 */
public class Store extends NamedEntity {

    private String webAddress;
    private ArrayList<Item> items;


    public Store(Long id, String name, String webAddress, ArrayList<Item> items) {
        super(id, name);
        this.webAddress = webAddress;
        this.items = items;
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
