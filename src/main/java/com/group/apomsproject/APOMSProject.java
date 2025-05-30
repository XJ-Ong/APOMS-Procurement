package com.group.apomsproject;

public class APOMSProject
{

    public static void main(String[] args)
    {
        HeaderRegistry.init();
        FileOperations fh = new FileOperations();
        Admin admintest = new Admin("A01", "Admin1", "Admin1pass", "Admin1Address", "Admin1Contact");
        fh.WriteFile(admintest);
        
        //MainGUI main = new MainGUI();
        //main.setVisible(true);
    }
}
