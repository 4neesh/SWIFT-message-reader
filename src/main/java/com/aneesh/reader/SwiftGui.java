package com.aneesh.reader;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SwiftGui extends JFrame {

    //singleton design

    private static SwiftGui guiSingleton = null;
    private static JTable table;
    private static int guiWidth = 600;
    private static int guiHeight = 200;
    public static final String NO_FILE_MESSAGE= "No files to display";
    String[] columnNames;
    String [][] tableData;
    private SwiftGui(){
    }

    public void buildTable(){

        setLayout(new FlowLayout());

        defineColumnNames();

    }

    public void buildData(MessageType[] mt940Dir) {

        String[][] fileContent;

        if(mt940Dir.length == 0){
            fileContent = new String[1][1];
            fileContent[0][0] = NO_FILE_MESSAGE;
        }
        else{
            fileContent = new String[mt940Dir.length][3];
            fileContent[0][0] = "Aneesh";
            fileContent[0][1] = "Mistry";
            fileContent[0][2] = "Test";


        }

        tableData = fileContent;


    }

    private void defineColumnNames() {
        columnNames = new String[3];
        columnNames[0] = "Filename";
        columnNames[1] = "Name";
        columnNames[2] = "Colour";

    }

    public static synchronized SwiftGui getGuiSingleton(){
        if(guiSingleton == null){
            guiSingleton = new SwiftGui();
        }

        return guiSingleton;
    }

    private void setDefaultGuiProperties() {
        guiSingleton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiSingleton.setSize(guiWidth,guiHeight);
        guiSingleton.setVisible(true);
        guiSingleton.setTitle("SWIFT Reader");


    }

    public void buildTableView() {


        table = new JTable(tableData, columnNames);

        table.setPreferredScrollableViewportSize(new Dimension(500  ,50));
        table.setFillsViewportHeight(true);
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane);

        setDefaultGuiProperties();

    }

    public static JTable getTable() {
        return table;
    }
}
