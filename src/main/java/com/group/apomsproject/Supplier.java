package com.group.apomsproject;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String contactInfo;
    
    public Supplier(String supplierID, String supplierName, String contactInfo)
    {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactInfo = contactInfo;
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

    public String getContactInfo() {
        return contactInfo;
    }
    
    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }
    
    public String toCSV() {
        return supplierID + "," + supplierName + "," + contactInfo;
    }
}
