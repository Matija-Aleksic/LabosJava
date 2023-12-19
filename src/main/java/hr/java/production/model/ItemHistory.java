package hr.java.production.model;

import java.util.Date;

public class ItemHistory {
    private int id;
    private int itemid;
    private String changeOn;
    private String oldValue;
    private String newValue;

    // Constructors
    public ItemHistory(int id, int itemid, String changeOn, String oldValue, String newValue) {
        this.id = id;
        this.itemid = itemid;
        this.changeOn = changeOn;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getIdString(){
        String temp = String.valueOf(id);
        return temp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemid() {
        return itemid;
    }
    public String getItemIdString(){
        String temp = String.valueOf(itemid);
        return temp;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getChangeOn() {
        return changeOn;
    }

    public void setChangeOn(String changeOn) {
        this.changeOn = changeOn;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
