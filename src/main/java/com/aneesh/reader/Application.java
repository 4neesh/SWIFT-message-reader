package com.aneesh.reader;

import java.io.File;
import java.util.Date;

public class Application {


    public static void main(String[] args) {

        System.out.println(new Date() + "   Obtaining GUI Singleton.");
        SwiftGui swiftGui = SwiftGui.getGuiSingleton();


        File[] mt940Dir = new MessageType("MT940").listFiles();


        System.out.println(new Date() + "   Building table in GUI.");
        swiftGui.buildTable();


        System.out.println(new Date() + "   Populating table.");
        swiftGui.buildTableView();

    }

}

