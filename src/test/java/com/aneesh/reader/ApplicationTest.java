package com.aneesh.reader;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationTest {

    private static SwiftGui swiftGui;

    @BeforeClass
    public static void setUp(){

        swiftGui = SwiftGui.getGuiSingleton();

    }

    @Test
    public void createsGuiInstance(){

        assertTrue(swiftGui != null);

    }


}
