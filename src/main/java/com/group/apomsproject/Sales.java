package com.group.apomsproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sales {
    
    private String salesID;
    private String itemID;
    private int quantitySold;
    private String dateCreated;
    private String SMID;

    public Sales(String salesID, String itemID, int quantitySold, String dateCreated, String SMID) {
        this.salesID = salesID;
        this.itemID = itemID;
        this.quantitySold = quantitySold;
        this.dateCreated = dateCreated;
        this.SMID = SMID;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
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
