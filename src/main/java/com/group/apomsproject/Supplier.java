package com.group.apomsproject;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String supplierContact;
    
    public Supplier(String supplierID, String supplierName, String supplierContact)
    {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierContact = supplierContact;

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
    
    public String toCSV() {
        return supplierID + "," + supplierName + "," + supplierContact;
    }
    
    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }
}
