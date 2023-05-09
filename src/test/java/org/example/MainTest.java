package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void TestMain() {
        Main.main(new String[]{"asd", "asd"});
        Assertions.assertTrue(5 > 4);
        System.out.println("printtest");
    }

    @Test
    void TestSumming() {
        Assertions.assertEquals(10, 10);
    }
}