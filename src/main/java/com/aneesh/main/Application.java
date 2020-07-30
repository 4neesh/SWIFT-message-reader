package com.aneesh.main;

import com.aneesh.messages.MessageType;
import com.aneesh.gui.SwiftGui;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Application {


    public static void main(String[] args) throws IOException {

        System.out.println(new Date() + "   Creating the GUI...");
        SwiftGui swiftGui = SwiftGui.getGuiSingleton();


        File[] mt940Dir = new MessageType("").getMessages(".");


        System.out.println(new Date() + "   Building the table in GUI...");
        swiftGui.buildTable();

        swiftGui.buildData(mt940Dir);

        System.out.println(new Date() + "   Populating table with file information...");
        swiftGui.buildTableView();

    }

}

