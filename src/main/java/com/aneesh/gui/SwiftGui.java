package com.aneesh.gui;

import com.aneesh.builder.TableBuilder;
import com.aneesh.reader.MessageTable;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SwiftGui extends JFrame {

    //singleton design

    private static SwiftGui guiSingleton = null;
    public static JTable table;
    private static int guiWidth = 600;
    private static int guiHeight = 400;
    public static final String NO_FILE_MESSAGE= "No files to display";
    public static TableBuilder tableBuilder;

    private SwiftGui(){
    }

    public void buildTable(){

        setLayout(new FlowLayout());

       tableBuilder.defineColumnNames();

    }

    public static synchronized SwiftGui getGuiSingleton(){
        if(guiSingleton == null){
            guiSingleton = new SwiftGui();
            tableBuilder = new TableBuilder();
        }

        return guiSingleton;
    }

    public void setDefaultGuiProperties() {

        guiSingleton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiSingleton.setSize(guiWidth,guiHeight);
        guiSingleton.setVisible(true);
        guiSingleton.setTitle("SWIFT Reader");

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

        tableBuilder.tableData = fileContent;


    }





}

