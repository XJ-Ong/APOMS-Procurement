package com.group.apomsproject;

public class APOMSProject
{

    public static void main(String[] args)
    {
        InventoryManager IM01 = new InventoryManager("I01", "IMtest", "1234", "PJ", "+6011-11112234");
        
        MainGUI main = new MainGUI();
        main.setVisible(true);
    }
}
