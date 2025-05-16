package com.group.apomsproject;

public class FinanceManager extends User{
    
    private int FMID;

    public FinanceManager(String userName, String userAddress, String userContact) {
        super(userName, userAddress, userContact);
        this.FMID = FMID;
    }
    
    public int getFMID() {
        return FMID;
    }

    public void setFMID(int FMID) {
        this.FMID = FMID;
    }
}
