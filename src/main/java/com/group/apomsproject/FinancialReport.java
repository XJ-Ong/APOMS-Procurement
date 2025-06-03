package com.group.apomsproject;

public class FinancialReport {
    
    private String FRID;
    private String ILID;
    private String itemID;
    private String supplierID;
    private double payAmount;
    private String dateCreated;
    private String FMID;

    public FinancialReport(String FRID, String ILID, String itemID, String supplierID, double payAmount, String dateCreated, String FMID) {
        this.FRID = FRID;
        this.ILID = ILID;
        this.itemID = itemID;
        this.supplierID = supplierID;
        this.payAmount = payAmount;
        this.dateCreated = dateCreated;
        this.FMID = FMID;
    }

    public String getFRID() {
        return FRID;
    }

    public void setFRID(String FRID) {
        this.FRID = FRID;
    }

    public String getILID() {
        return ILID;
    }

    public void setILID(String ILID) {
        this.ILID = ILID;
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

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getFMID() {
        return FMID;
    }

    public void setFMID(String FMID) {
        this.FMID = FMID;
    }
}
