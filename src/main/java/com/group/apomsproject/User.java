package com.group.apomsproject;

public abstract class User
{
    protected String userName;
    protected String userPassword;
    protected String userAddress;
    protected String userContact;

    public User(String userName, String userPassword, String userAddress, String userContact) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userContact = userContact;
    }

    public abstract String getUserName();

    public abstract void setUserName(String userName);

    public abstract String getUserAddress();

    public abstract void setUserAddress(String userAddress);

    public abstract String getUserContact();

    public abstract void setUserContact(String userContact);

    public abstract String getUserPassword();

    public abstract void setUserPassword(String userPassword);
}