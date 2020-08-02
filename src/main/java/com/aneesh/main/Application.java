package com.aneesh.main;

import com.aneesh.messages.MessageType;
import com.aneesh.gui.SwiftGui;

import java.io.File;
import java.util.Date;

public class Application {


    public static void main(String[] args){

        System.out.println(new Date() + "   Creating the GUI...");
        SwiftGui swiftGui = SwiftGui.getGuiSingleton();


        File[] mt940Dir = new MessageType("").getMessages(".");


        System.out.println(new Date() + "   Creating the table frame...");
        swiftGui.buildTable();


        System.out.println(new Date() + "   Populating table with file information...");
        swiftGui.tableBuilder.populateTable(mt940Dir);


        swiftGui.tableBuilder.setTableProperties();

    }

}

