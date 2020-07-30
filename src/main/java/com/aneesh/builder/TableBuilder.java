package com.aneesh.builder;

import com.aneesh.gui.SwiftGui;
import com.aneesh.reader.MessageTable;

import javax.swing.*;
import java.awt.*;

import static com.aneesh.gui.SwiftGui.table;

public class TableBuilder {

    public String[] columnNames;
    public String [][] tableData;
    SwiftGui swiftGui = SwiftGui.getGuiSingleton();

    public void buildTableView() {


        table = new MessageTable(tableData, columnNames);
        table.setAutoCreateRowSorter(true);
        table.setPreferredScrollableViewportSize(new Dimension(500  ,300));
        table.setFillsViewportHeight(true);
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane);

        swiftGui.setDefaultGuiProperties();

    }

    public void defineColumnNames() {
        columnNames = new String[7];
        columnNames[0] = "Filename";
        columnNames[1] = "Account (25)";
        columnNames[2] = "Sequence (28c)";
        columnNames[3] = "Opening (60a)";
        columnNames[4] = "Closing (64)";
        columnNames[5] = "BIC (52a)";
        columnNames[6] = "BIC (57a)";

    }

}
