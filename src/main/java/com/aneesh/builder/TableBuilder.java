package com.aneesh.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TableBuilder {

    public String[] columnNames;
    public static final String NO_FILE_MESSAGE= "No files to display";
    public String [][] tableData;



    public String[] defineColumnNames() {
        columnNames = new String[7];
        columnNames[0] = "Filename";
        columnNames[1] = "Account (25)";
        columnNames[2] = "Sequence (28c)";
        columnNames[3] = "Opening (60a)";
        columnNames[4] = "Closing (64)";
        columnNames[5] = "BIC (52a)";
        columnNames[6] = "BIC (57a)";
        return columnNames;
    }

    public void buildData(File[] mt940Dir) throws IOException {

        String[][] fileContent;

        if(mt940Dir.length == 0){
            fileContent = new String[1][1];
            fileContent[0][0] = NO_FILE_MESSAGE;
        }
        else{
            fileContent = new String[mt940Dir.length][7];

            int i = 0;
            for(File f: mt940Dir){
                BufferedReader reader = new BufferedReader(new FileReader(f.getAbsolutePath()));
                String line = reader.readLine();
                int j = 0;

                fileContent[i][j] = f.getName();
                j++;
                while(line != null){

                    fileContent[i][j] = line;
                    j++;
                    line = reader.readLine();
                }
                i++;
            }

        }

        tableData = fileContent;


    }


}
