package com.group.apomsproject;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String itemID;
    
    public Supplier(String supplierID, String supplierName, String itemID)
    {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.itemID = itemID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }
}
