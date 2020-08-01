package com.aneesh.builder;

import com.aneesh.gui.SwiftGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.HashMap;

public class TableBuilder {

    private static JTable table;
    public String[] columnNames;
    public static final String NO_FILE_MESSAGE= "No files to display";
    public String [][] tableData;
    public SwiftGui swiftGui = SwiftGui.getGuiSingleton();
    private static HashMap<String, Integer> tags;
    private static int columnNumbers;
    private int tableHeight = 300;
    private int tableWidth = 700;

    public String[] defineColumnNames() {
        tags = new HashMap<String, Integer>();
        columnNames = new String[7];
        columnNames[0] = "Filename";
        columnNames[1] = "Account (25)";
        columnNames[2] = "Sequence (28c)";
        columnNames[3] = "Opening (60a)";
        columnNames[4] = "Closing (64)";
        columnNames[5] = "BIC (52a)";
        columnNames[6] = "BIC (57a)";
        tags.put("25:", 1);
        tags.put("28C", 2);
        tags.put("60A", 3);
        tags.put("64:", 4);
        tags.put("52A", 5);
        tags.put("57A", 6);
        columnNumbers = columnNames.length;

        return columnNames;
    }

    public void setTableView() {



        table = new JTable(tableData, columnNames);
        table.setAutoCreateRowSorter(true);
        table.setPreferredScrollableViewportSize(new Dimension(tableWidth,tableHeight));
        table.setFillsViewportHeight(true);
        JScrollPane jScrollPane = new JScrollPane(table);

        table.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                if(col == 0){
                    table.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                else{
                    table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int col = table.columnAtPoint(e.getPoint());
                if(col == 0){

                    int r = table.getSelectedRow();
                    try {
                        Desktop.getDesktop().open(new File("./" + table.getValueAt(r, 0)));

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        swiftGui.add(jScrollPane);

        swiftGui.setProperties();

    }


    public void populateTable(File[] mt940Dir) {

        String[][] fileContent = new String[0][];

        if(noFilesFound(mt940Dir)){

            setEmptyTable(fileContent);

        }
        else{
            fileContent = new String[mt940Dir.length][columnNumbers];

            int row = 0;
            for(final File file: mt940Dir){
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                    String line = reader.readLine();
                    int col = 0;


                    fileContent[row][col] = file.getName();

                    col++;
                    while(line != null){

                        populateTableWithLine(line, fileContent, row);

                        line = reader.readLine();
                    }

                    for(int i = 0; i<columnNumbers; i++){

                        if(fileContent[row][i]!= null){
                        }else{
                            fileContent[row][i] = "{NO VALUE}";
                        }

                    }


                    row++;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        tableData = fileContent;

    }

    private void populateTableWithLine(String line, String[][] fileContent, int row) {
        String key = line.substring(0,3);
        String content = line.substring(3);
        content = content.replace(":", "");

        if(tags.containsKey(key)){
            fileContent[row][tags.get(key)] = content;
        }
    }

    private boolean noFilesFound(File[] mt940Dir) {
        return mt940Dir.length == 0;
    }
    private void setEmptyTable(String[][] fileContent){
        fileContent = new String[1][1];
        fileContent[0][0] = NO_FILE_MESSAGE;
    }


}
