package hr.java.production.model;

public class Store {
    private String name;
    private String webAddress;
    private Item[] items;
    private Address adresa;

    public Store(String name, String webAddress, Item[] items, Address adresa) {
        this.name = name;
        this.webAddress = webAddress;
        this.items = items;
        this.adresa = adresa;
    }

    public Store(String name, String webAddress, Item[] items) {
        this.name = name;
        this.webAddress = webAddress;
        this.items = items;
    }

    public Address getAdresa() {
        return adresa;
    }

    public void setAdresa(Address adresa) {
        this.adresa = adresa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public Address getAddress() {
        return this.adresa;
    }
}
