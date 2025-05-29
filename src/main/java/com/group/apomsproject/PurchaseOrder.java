package com.group.apomsproject;

public class PurchaseOrder {
    private String POID;
    private String PRID;
    private String itemID;
    private int orderQuantity;
    private String supplierID;
    private String status;
    private String dateCreated;
    private String PMID;

    public PurchaseOrder(String POID, String PRID, String itemID, int orderQuantity, String supplierID, String status, String dateCreated, String PMID) {
        this.POID = POID;
        this.PRID = PRID;
        this.itemID = itemID;
        this.orderQuantity = orderQuantity;
        this.supplierID = supplierID;
        this.status = status;
        this.dateCreated = dateCreated;
        this.PMID = PMID;
    }

    public String getPOID() {
        return POID;
    }

    public void setPOID(String POID) {
        this.POID = POID;
    }

    public String getPRID() {
        return PRID;
    }

    public void setPRID(String PRID) {
        this.PRID = PRID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
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

    public String getPMID() {
        return PMID;
    }

    public void setPMID(String PMID) {
        this.PMID = PMID;
    }
}
