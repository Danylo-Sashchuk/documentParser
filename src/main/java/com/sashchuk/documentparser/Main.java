package com.sashchuk.documentparser;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Main {
    public static void main(String[] args) throws TesseractException {
        Tesseract tesseract = new Tesseract();
//        tesseract.setDatapath(Settings.TESSETACT_DATAPATH);
        File file = new File("/Users/Danylo/Desktop/Workspace/documentparser/src/test/resources/ProcessEssay.pdf");
        System.setProperty("jna.library.path", args[0]);
        String result = tesseract.doOCR(file);
        System.out.println(result);
    }
}