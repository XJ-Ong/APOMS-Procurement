package com.group.apomsproject;

public class ImportList {
    private String ILID;
    private String POID;
    private String itemID;
    private String supplierID;
    private int receivedQuantity;
    private String status;
    private String dateCreated;
    private String FMID;

    public ImportList(String ILID, String POID, String itemID, String supplierID, int receivedQuantity, String status, String dateCreated, String FMID) {
        this.ILID = ILID;
        this.POID = POID;
        this.itemID = itemID;
        this.supplierID = supplierID;
        this.receivedQuantity = receivedQuantity;
        this.status = status;
        this.dateCreated = dateCreated;
        this.FMID = FMID;
    }

    public String getILID() {
        return ILID;
    }

    public void setILID(String ILID) {
        this.ILID = ILID;
    }

    public String getPOID() {
        return POID;
    }

    public void setPOID(String POID) {
        this.POID = POID;
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

    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
