package hr.java.production.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * The type Factory.
 */
public class Factory extends NamedEntity {

    private Address address;
    private Item[] items;


    /**
     * Instantiates a new Factory.
     *
     * @param name    the name
     * @param address the address
     * @param items   the items
     */
    public Factory(String name, Address address, Item[] items) {
        super(name);
        this.address = address;
        this.items = items;
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
    public Item[] getItems() {
        return items;
    }

    /**
     * Sets items.
     *
     * @param items the items
     */
    public void setItems(Item[] items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Factory factory)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getAddress(), factory.getAddress()) && Arrays.equals(getItems(), factory.getItems());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), getAddress());
        result = 31 * result + Arrays.hashCode(getItems());
        return result;
    }
}
