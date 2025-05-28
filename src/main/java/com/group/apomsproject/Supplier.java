package com.group.apomsproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    
    public static String generateSupplierID(String supplierFile) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(supplierFile))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading sales file: " + e.getMessage());
        }

        // Format the ID with leading zeros, e.g., S001, S010
        return String.format("SU%03d", count);
    }
}
