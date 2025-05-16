package com.group.apomsproject;

public class Supplier {
    private int supplierID;
    private String supplierName;
    private int itemID;
    
    public Supplier(int supplierID, String supplierName, int itemID)
    {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.itemID = itemID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
