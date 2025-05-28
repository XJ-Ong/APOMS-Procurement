package com.group.apomsproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PurchaseRequisition {
   
    private String PRID;
    private int quantity;
    private String deliveryDate;
    private String status;
    private String SMID;  

    public String getPRID() {
        return PRID;
    }

    public void setPRID(String PRID) {
        this.PRID = PRID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSMID() {
        return SMID;
    }

    public void setSMID(String SMID) {
        this.SMID = SMID;
    }
    
    public PurchaseRequisition(String PRID, int quantity, String deliveryDate, String status, String SMID) {
        this.PRID = PRID;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.SMID = SMID;
    }
    
    public String toCSV() {
        return PRID + "," + quantity + "," + deliveryDate + "," + status + "," + SMID;
    }
    
    public static String generateRequisitionID(String requisitionFile) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(requisitionFile))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error reading requisition file: " + e.getMessage());
        }

        // Format the ID with leading zeros, e.g., R001, R010
        return String.format("RE%03d", count);
    }
 }
