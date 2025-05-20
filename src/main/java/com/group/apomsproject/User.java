package com.group.apomsproject;

public class User
{
    protected String userName;
    protected String userPassword;
    protected String userAddress;
    protected String userContact;

    public User(String userName, String userPassword, String userAddress, String userContact)
    {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userContact = userContact;
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