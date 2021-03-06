package com.aneesh.builder;

import com.aneesh.gui.SwiftGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.HashMap;

public class TableBuilder {

    public static JTable table;
    public String[] columnNames;
    public static final String NO_FILE_MESSAGE= "No files to display";
    public static final String NO_VALUE = "{NO VALUE}";
    public Object [][] tableData;
    public SwiftGui swiftGui = SwiftGui.getGuiSingleton();
    private static HashMap<String, Integer> tags;
    private static int columnNumbers;
    private int tableHeight = (int)Math.round(SwiftGui.guiHeight * 0.9);
    private int tableWidth = (int)Math.round(SwiftGui.guiWidth * 0.9);
    private static int row = 0;
    private static DefaultTableModel model;

    public TableBuilder(){
        tags = new HashMap<String, Integer>();

        tags.put(":20:", 1);
        tags.put(":25:", 2);
        tags.put(":28C", 3);
        tags.put(":60F", 4);
        tags.put(":64:", 5);
        tags.put(":62F", 6);
    }

    public void defineColumnNames() {
        columnNames = new String[7];
        columnNames[0] = "Filename";
        columnNames[1] = "Sender Reference (20)";
        columnNames[2] = "Account Number (25)";
        columnNames[3] = "Sequence (28C)";
        columnNames[4] = "Opening (60F)";
        columnNames[5] = "Closing (64)";
        columnNames[6] = "Current balance (62F)";

        columnNumbers = columnNames.length;


    }

    public void setTableProperties() {


        setDefaultModel();

        table = new JTable(model);

        setTableSort();
        setTableScrollSize();
        setTableFillViewPort();
        table.setDefaultRenderer(Object.class, new CustomTableRenderer());
        JScrollPane jScrollPane = new JScrollPane(table);

        addTableCursorListener();
        addTableFileOpener();

        swiftGui.add(jScrollPane);
        swiftGui.setProperties();

    }

    private void setDefaultModel() {

        defineColumnNames();
        model = new DefaultTableModel(tableData, columnNames);

    }

    public void populateTable(File[] mt940Dir) {

        String[][] fileContent = new String[0][];

        if(noFilesFound(mt940Dir)){
            setEmptyTable(fileContent);
        }
        else{
            fileContent = new String[mt940Dir.length][columnNumbers];


            for(final File file: mt940Dir){

                processFileIntoTable(file, fileContent);

            }

        }

        tableData = fileContent;

    }

    private void processFileIntoTable(File file, String[][] fileContent) {


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
                    fileContent[row][i] = NO_VALUE;
                }

            }


            row++;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void addTableFileOpener() {
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
    }

    private void addTableCursorListener() {
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
    }

    private void setTableFillViewPort() {
        table.setFillsViewportHeight(true);
    }

    private void setTableScrollSize() {
        table.setPreferredScrollableViewportSize(new Dimension(tableWidth,tableHeight));
    }

    private void setTableSort() {
        table.setAutoCreateRowSorter(true);
    }



    private void populateTableWithLine(String line, String[][] fileContent, int row) {
        if(line.length() > 4) {
            String key = line.substring(0, 4);
            String content = line.substring(4);
            content = content.replace(":", "");

            if (tags.containsKey(key)) {
                fileContent[row][tags.get(key)] = content;
            }
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

