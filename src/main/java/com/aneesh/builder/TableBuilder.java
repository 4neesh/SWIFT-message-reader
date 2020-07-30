package com.aneesh.builder;

import com.aneesh.gui.SwiftGui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TableBuilder {

    private static JTable table;
    public String[] columnNames;
    public static final String NO_FILE_MESSAGE= "No files to display";
    public String [][] tableData;
    public SwiftGui swiftGui = SwiftGui.getGuiSingleton();


    public String[] defineColumnNames() {
        columnNames = new String[7];
        columnNames[0] = "Filename";
        columnNames[1] = "Account (25)";
        columnNames[2] = "Sequence (28c)";
        columnNames[3] = "Opening (60a)";
        columnNames[4] = "Closing (64)";
        columnNames[5] = "BIC (52a)";
        columnNames[6] = "BIC (57a)";
        return columnNames;
    }

    public void buildTableView() {

        table = new JTable(tableData, columnNames);
        table.setAutoCreateRowSorter(true);
        table.setPreferredScrollableViewportSize(new Dimension(500  ,300));
        table.setFillsViewportHeight(true);
        JScrollPane jScrollPane = new JScrollPane(table);
        swiftGui.add(jScrollPane);

        swiftGui.setProperties();

    }


    public void populateTable(File[] mt940Dir) {

        String[][] fileContent;

        if(mt940Dir.length == 0){
            fileContent = new String[1][1];
            fileContent[0][0] = NO_FILE_MESSAGE;
        }
        else{
            fileContent = new String[mt940Dir.length][7];

            int i = 0;
            for(File f: mt940Dir){
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader(f.getAbsolutePath()));
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
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        tableData = fileContent;

    }


}
