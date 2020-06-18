package com.aneesh.reader;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Application {


    public static void main(String[] args) throws IOException {

        System.out.println(new Date() + "   Obtaining GUI Singleton.");
        SwiftGui swiftGui = SwiftGui.getGuiSingleton();


        File[] mt940Dir = new MessageType("").getMessages(".");


        System.out.println(new Date() + "   Building table in GUI.");
        swiftGui.buildTable();

        swiftGui.buildData(mt940Dir);

        System.out.println(new Date() + "   Populating table.");
        swiftGui.buildTableView();

    }

}

