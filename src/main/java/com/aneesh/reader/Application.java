package com.aneesh.reader;

public class Application {


    public static void main(String[] args) {

        SwiftGui swiftGui = SwiftGui.getGuiSingleton();

        swiftGui.buildTable();

        swiftGui.populateTable();

    }

}

