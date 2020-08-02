package com.aneesh.builder;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CustomTableRenderer implements TableCellRenderer {

    public CustomTableRenderer(){
        System.out.println("tableRenderConstructor");
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JTextField editor = new JTextField();

        editor.setForeground(Color.RED);
        String val = value.toString();
        System.out.println(val);
        System.out.println(val.substring(val.length()-4));
        if(val.substring(val.length()-4).equals(".txt")){
            editor.setForeground(Color.BLUE);
        }

        return editor;
    }

}
