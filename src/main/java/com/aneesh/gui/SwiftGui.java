package com.aneesh.gui;

import com.aneesh.builder.TableBuilder;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class SwiftGui extends JFrame {

    private static SwiftGui guiSingleton = null;
    public static int guiWidth = 1200;
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
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        guiSingleton.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(guiSingleton.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("status");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);

        guiSingleton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiSingleton.setSize(guiWidth,guiHeight);
        guiSingleton.setVisible(true);
        guiSingleton.setTitle("MT Reader");

        getContentPane().add(new JScrollPane(tableBuilder.table), BorderLayout.CENTER);
        pack();

    }

    @Override
    public void print(Graphics g) {
        super.print(g);
    }
}

