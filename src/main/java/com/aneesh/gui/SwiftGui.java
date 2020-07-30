package com.aneesh.gui;

import com.aneesh.builder.TableBuilder;

import javax.swing.*;
import java.awt.*;

public class SwiftGui extends JFrame {

    //singleton design

    private static SwiftGui guiSingleton = null;
    private static JTable table;
    private static int guiWidth = 600;
    private static int guiHeight = 400;
    public static TableBuilder tableBuilder;
    private String[] columnNames;

    private SwiftGui(){
    }

    public void buildTable(){

        setLayout(new FlowLayout());

        columnNames = tableBuilder.defineColumnNames();

    }


    public static synchronized SwiftGui getGuiSingleton(){
        if(guiSingleton == null){
            guiSingleton = new SwiftGui();
            tableBuilder = new TableBuilder();
        }

        return guiSingleton;
    }

    private void setProperties() {
        guiSingleton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiSingleton.setSize(guiWidth,guiHeight);
        guiSingleton.setVisible(true);
        guiSingleton.setTitle("SWIFT Reader");


    }

    public void buildTableView() {


        table = new JTable(tableBuilder.tableData, columnNames);
        table.setAutoCreateRowSorter(true);
        table.setPreferredScrollableViewportSize(new Dimension(500  ,300));
        table.setFillsViewportHeight(true);
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane);

        setProperties();

    }

}

