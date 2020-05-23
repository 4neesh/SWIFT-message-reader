package com.aneesh.reader;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SwiftGui extends JFrame {

    //singleton design

    private static SwiftGui guiSingleton = null;
    private static JTable table;
    private static int guiWidth = 600;
    private static int guiHeight = 200;

    private SwiftGui(){
    }

    public void buildTable(){
        guiSingleton.setLayout(new FlowLayout());

        String[] columnNames = {"Filename", "Eye", "Gender"};

        Object[][] data = {
                {"Aneesh", "mistry", "male"},{"J", "k", "female"}
        };

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500  ,50));
        table.setFillsViewportHeight(true);

        JScrollPane jp = new JScrollPane(table);
        add(jp);

        guiSingleton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiSingleton.setSize(guiWidth,guiHeight);
        guiSingleton.setVisible(true);
        guiSingleton.setTitle("SWIFT Reader");
    }

    public static synchronized SwiftGui getGuiSingleton(){
        if(guiSingleton == null){
            guiSingleton = new SwiftGui();
        }
        return guiSingleton;
    }

    public void populateTable() {
        try {
            InputStream is = new FileInputStream("hello.txt");
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }

            String fileAsString = sb.toString();
            System.out.println("Contents : " + fileAsString);
        }
        catch(Exception e){
            System.out.println("Caught exception: " + e.getMessage());
        }


    }

    public static JTable getTable() {
        return table;
    }
}
