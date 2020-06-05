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
    private static final String NO_FILE_MESSAGE= "No files to display";
    String[] columnNames;

    private SwiftGui(){
    }

    public void buildTable(){
        guiSingleton.setLayout(new FlowLayout());

        columnNames = new String[3];
        columnNames[0] = "Filename";
        columnNames[1] = "Name";
        columnNames[2] = "Colour";


        String[][] data = populateTable();

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500  ,50));
        table.setFillsViewportHeight(true);

        JScrollPane jp = new JScrollPane(table);
        add(jp);
        setDefaultGuiProperties();


    }

    public static synchronized SwiftGui getGuiSingleton(){
        if(guiSingleton == null){
            guiSingleton = new SwiftGui();
        }
        return guiSingleton;
    }

    private static void setDefaultGuiProperties() {
        guiSingleton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiSingleton.setSize(guiWidth,guiHeight);
        guiSingleton.setVisible(true);
        guiSingleton.setTitle("SWIFT Reader");
    }

    public String[][] populateTable() {


        String[][] fileContent;
        File[] mt940Dir = new File("MT940").listFiles();

        if(mt940Dir.length == 0){
            fileContent = new String[1][1];
            fileContent[0][0] = NO_FILE_MESSAGE;
        }
        else{
            fileContent = new String[3][mt940Dir.length];
            fileContent[0][0] = "Aneesh";
            fileContent[0][1] = "Mistry";
            fileContent[0][2] = "Test";

            for(File f : mt940Dir){
                System.out.println(f.getAbsolutePath());
            }
        }


        return fileContent;

//        try {
//            InputStream is = new FileInputStream("hello.txt");
//            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
//            String line = buf.readLine();
//            StringBuilder sb = new StringBuilder();
//            while (line != null) {
//                sb.append(line).append("\n");
//                line = buf.readLine();
//            }
//
//            String fileAsString = sb.toString();
//            System.out.println("Contents : " + fileAsString);
//        }
//        catch(Exception e){
//            System.out.println("Caught exception: " + e.getMessage());
//        }


    }

    public static JTable getTable() {
        return table;
    }
}
