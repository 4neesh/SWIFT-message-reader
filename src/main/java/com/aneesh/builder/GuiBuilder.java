package com.aneesh.builder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GuiBuilder {

    public GuiBuilder(){

        Properties prop = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");

        if(input != null){
            try {
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{

        }


    }

}
