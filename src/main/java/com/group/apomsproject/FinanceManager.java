package com.group.apomsproject;

public class FinanceManager extends User{
    
    private int FMID;

     public FinanceManager(int FMID, String userName, String userPassword, String userAddress, String userContact)
    {
        super(userName, userPassword, userAddress, userContact);
        this.FMID = FMID;
    }

    public int getFMID() {
        return FMID;
    }

    public void setFMID(int FMID) {
        this.FMID = FMID;
    }
}
