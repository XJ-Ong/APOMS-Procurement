package com.group.apomsproject;

public class ItemSupplierMap {
    private String itemID;
    private String supplierID;

    public ItemSupplierMap(String itemID, String supplierID) {
        this.itemID = itemID;
        this.supplierID = supplierID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }
}
