package com.aneesh.reader;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationTest {

    private static SwiftGui swiftGui;
    private static JTable table;

    @BeforeClass
    public static void setUp(){

        swiftGui = SwiftGui.getGuiSingleton();
        swiftGui.buildTable();
    }

    @Test
    public void guiExists(){

        assertTrue(swiftGui != null);

    }

    @Test
    public void tableBuilt(){

        assertTrue(swiftGui.getTable() != null);

    }

    @Test
    public void emptyTableIfNoFiles(){



    }

    @Test
    public void populatedTableIfFiles(){



    }

}
