package com.group.apomsproject;

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
 }
