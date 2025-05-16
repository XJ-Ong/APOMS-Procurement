package com.group.apomsproject;

public class InventoryManager extends User
{
    private int IMID;

    public InventoryManager(int IMID, String userName, String userAddress, String userContact) {
        super(userName, userAddress, userContact, userPassword);
        this.IMID = IMID;
    }

    public int getIMID() {
        return IMID;
    }

    public void setIMID(int IMID) {
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
