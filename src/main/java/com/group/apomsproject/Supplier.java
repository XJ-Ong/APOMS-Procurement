package com.group.apomsproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String supplierAddress;
    private String supplierContact;
    
    public Supplier(String supplierID, String supplierName, String supplierAddress, String supplierContact)
    {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
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

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
    
    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }
}
