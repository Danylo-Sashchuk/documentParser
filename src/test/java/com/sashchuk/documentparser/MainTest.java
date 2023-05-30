package com.sashchuk.documentparser;

import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void TestMain() throws TesseractException {
        Main.main(new String[]{"/opt/homebrew/Cellar/tesseract/5.3.1/lib"});
        System.out.println("printtest");
    }
}