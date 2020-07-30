package com.aneesh.messages;

import java.io.File;
import java.io.FilenameFilter;

public class MessageType extends File {


    public MessageType(String pathname) {
        super(pathname);
    }


    public File[] getMessages(String pathname){

        File[] mt940Dir = new MessageType(pathname).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });

        return mt940Dir;

    }
}
