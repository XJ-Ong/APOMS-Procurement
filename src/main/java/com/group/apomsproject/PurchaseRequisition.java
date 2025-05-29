package com.group.apomsproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PurchaseRequisition {
    private String PRID;
    private String itemID;
    private int requiredQuantity;
    private String status;
    private String dateCreated;
    private String SMID;

    public PurchaseRequisition(String PRID, String itemID, int requiredQuantity, String status, String dateCreated, String SMID) {
        this.PRID = PRID;
        this.itemID = itemID;
        this.requiredQuantity = requiredQuantity;
        this.status = status;
        this.dateCreated = dateCreated;
        this.SMID = SMID;
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

    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(int requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
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

    public String getSMID() {
        return SMID;
    }

    public void setSMID(String SMID) {
        this.SMID = SMID;
    }
}
