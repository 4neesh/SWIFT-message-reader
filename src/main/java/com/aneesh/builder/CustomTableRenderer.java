package com.aneesh.builder;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CustomTableRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JTextField editor = new JTextField();
                
        if(value != null){
            editor.setText(value.toString());
            if(value.toString().equals(TableBuilder.NO_VALUE)){
                editor.setForeground(Color.GRAY);
            }
        }

        if(column==0){
            editor.setForeground(Color.BLUE);
        }

        return editor;
    }

}
