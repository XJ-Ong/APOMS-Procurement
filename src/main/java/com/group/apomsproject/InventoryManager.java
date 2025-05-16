package com.group.apomsproject;

public class InventoryManager extends User
{
    private String IMID;

    public InventoryManager(String IMID, String userName, String userPassword, String userAddress, String userContact)
    {
        super(userName, userPassword, userAddress, userContact);
        this.IMID = IMID;
    }

    public String getIMID() {
        return IMID;
    }

    public void setIMID(String IMID) {
        this.IMID = IMID;
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
