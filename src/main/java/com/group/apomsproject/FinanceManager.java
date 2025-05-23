package com.group.apomsproject;

public class FinanceManager extends User{
    
    private String FMID;

     public FinanceManager(String FMID, String userName, String userPassword, String userAddress, String userContact)
    {
        super(userName, userPassword, userAddress, userContact);
        this.FMID = FMID;
    }

    public String getFMID() {
        return FMID;
    }

    public void setFMID(String FMID) {
        this.FMID = FMID;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
