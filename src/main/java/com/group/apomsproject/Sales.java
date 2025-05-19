package com.group.apomsproject;

import java.time.LocalDate;

public class Sales {
    
    private String salesID;
    private String itemCode;
    private int quantitySold;
    private LocalDate salesDate;
    private String SMID;

    public Sales(String salesID, String itemCode, int quantitySold, LocalDate salesDate, String SMID) {
        this.salesID = salesID;
        this.itemCode = itemCode;
        this.quantitySold = quantitySold;
        this.salesDate = salesDate;
        this.SMID = SMID;
    }

    public String getSalesID() {
        return salesID;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public LocalDate getSalesDate() {
        return salesDate;
    }
}
