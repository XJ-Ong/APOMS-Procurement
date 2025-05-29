package com.group.apomsproject;

public class Item {
    private String itemID;
    private String itemName;
    private double price;
    private int quantity;
    private int ROL;

    public Item(String itemID, String itemName, double price, int quantity, int ROL) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.ROL = ROL;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getROL() {
        return ROL;
    }

    public void setROL(int ROL) {
        this.ROL = ROL;
    }
}
