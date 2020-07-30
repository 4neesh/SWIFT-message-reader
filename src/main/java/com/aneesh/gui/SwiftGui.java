package com.aneesh.gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SwiftGui extends JFrame {

    //singleton design

    private static SwiftGui guiSingleton = null;
    private static JTable table;
    private static int guiWidth = 600;
    private static int guiHeight = 400;
    public static final String NO_FILE_MESSAGE= "No files to display";
    String[] columnNames;
    String [][] tableData;

    private SwiftGui(){
    }

    public void buildTable(){

        setLayout(new FlowLayout());

        defineColumnNames();

    }

    public void buildData(File[] mt940Dir) throws IOException {

        String[][] fileContent;

        if(mt940Dir.length == 0){
            fileContent = new String[1][1];
            fileContent[0][0] = NO_FILE_MESSAGE;
        }
        else{
            fileContent = new String[mt940Dir.length][7];

            int i = 0;
            for(File f: mt940Dir){
                BufferedReader reader = new BufferedReader(new FileReader(f.getAbsolutePath()));
                String line = reader.readLine();
                int j = 0;

                fileContent[i][j] = f.getName();
                j++;
                while(line != null){

                    fileContent[i][j] = line;
                    j++;
                    line = reader.readLine();
                }
                i++;
            }

        }

        tableData = fileContent;


    }

    private void defineColumnNames() {
        columnNames = new String[7];
        columnNames[0] = "Filename";
        columnNames[1] = "Account (25)";
        columnNames[2] = "Sequence (28c)";
        columnNames[3] = "Opening (60a)";
        columnNames[4] = "Closing (64)";
        columnNames[5] = "BIC (52a)";
        columnNames[6] = "BIC (57a)";

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
        table.setAutoCreateRowSorter(true);
        table.setPreferredScrollableViewportSize(new Dimension(500  ,300));
        table.setFillsViewportHeight(true);
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane);

        setDefaultGuiProperties();

    }

    public static JTable getTable() {

        return table;
    }
}

