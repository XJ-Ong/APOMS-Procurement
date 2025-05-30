package com.group.apomsproject;

public class APOMSProject
{

    public static void main(String[] args)
    {
        HeaderRegistry.init();
        MainGUI main = new MainGUI();
        main.setVisible(true);
    }
}
