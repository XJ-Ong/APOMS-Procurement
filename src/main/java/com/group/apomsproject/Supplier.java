package com.group.apomsproject;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String itemCode;
    
    public Supplier(String supplierID, String supplierName, String itemCode)
    {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.itemCode = itemCode;
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

    public String getItemCode() {
        return itemCode;
    }
}
