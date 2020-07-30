package com.aneesh.reader;

import com.aneesh.gui.SwiftGui;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class SwiftGuiTest {

    @BeforeClass
    public static void BeforeGuiTest(){
        SwiftGui gui = SwiftGui.getGuiSingleton();
    }

    @Test
    public void guiInstanceExists(){

        assertFalse(SwiftGui.getGuiSingleton()==null);
    }


    @Test
    public void guiInstanceNotExists(){



    }
}
