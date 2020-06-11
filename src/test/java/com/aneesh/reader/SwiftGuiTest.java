package com.aneesh.reader;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

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
