package com.aneesh.reader;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.Assert.*;

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

        MessageType[] emptyMessage = new MessageType[0];

        //given
        MessageType mt = Mockito.mock(MessageType.class);
        Mockito.when(mt.getMessages()).thenReturn(emptyMessage);

        //when
        swiftGui.buildData(mt.getMessages());

        //then
        assertEquals(swiftGui.tableData[0][0], swiftGui.NO_FILE_MESSAGE);
        

    }

    @Test
    public void populatedTableIfFiles(){



    }

}
