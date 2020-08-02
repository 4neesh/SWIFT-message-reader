package com.aneesh.gui;

import com.aneesh.builder.TableBuilder;

import javax.swing.*;
import java.awt.*;

public class SwiftGui extends JFrame {

    private static SwiftGui guiSingleton = null;
    public static int guiWidth = 800;
    public static int guiHeight = 400;
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

    public void setProperties() {
        guiSingleton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiSingleton.setSize(guiWidth,guiHeight);
        guiSingleton.setVisible(true);
        guiSingleton.setTitle("MT Reader");
        getContentPane().add(new JScrollPane(tableBuilder.table), BorderLayout.CENTER);
        pack();

    }

}

