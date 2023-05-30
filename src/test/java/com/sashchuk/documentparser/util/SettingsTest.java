package com.sashchuk.documentparser.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SettingsTest {

    @Test
    void getProperty() {

    }

    @Test
    void getInstance() {
        Settings.getInstance();
        Assertions.assertEquals(1, 2);
    }

    @Test
    void testGetInstance() {
    }
}