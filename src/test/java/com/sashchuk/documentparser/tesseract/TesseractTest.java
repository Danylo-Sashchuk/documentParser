package com.sashchuk.documentparser.tesseract;

import com.sashchuk.documentparser.util.Config;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TesseractTest {

    static Tesseract tesseract;

    @BeforeAll
    static void beforeAll() {
        Config.recreate();
        System.setProperty("jna.library.path", Config.getProperty("JNA_LIBRARY_PATH"));
    }


    @Test
    void ocr() throws TesseractException, InterruptedException {
        tesseract = new Tesseract();
        Tesseract tesseract1 = new Tesseract();
        tesseract.setDatapath(Config.getProperty("TESSETACT_DATAPATH"));
        tesseract1.setDatapath(Config.getProperty("TESSETACT_DATAPATH"));


        File file = new File("src/test/resources/ProcessEssay.pdf");
        String expected = """
                Write Your Title Here
                                
                After outlining about the topic “How to be a successful college student,” start your
                paragraph by indenting it. Then, continue with your topic sentence defis remember to include the
                topic “successful student” and the controlling idea “steps” in your topic sentence. After you write
                your TS, provide the first supporting detail, which should be the first important step. Then
                provide the two minors for your SD1. Remember to provide an example and explanation for each
                minor. Continue your paragraph following the structure learned in class.
                """;

        Thread first = new Thread(() -> {
            try {
                String actual = tesseract.doOCR(file);
                System.out.println(actual);
            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }
        });

        File file1 = new File("src/test/resources/ProcessEssay1.pdf");
        Thread second = new Thread(() -> {
            try {
                String actual = tesseract1.doOCR(file1);
                System.out.println(actual);
            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }
        });


        first.start();
        second.start();

        first.join();
        second.join();

        //        Assertions.assertThat(expected)
        //                .isEqualTo(actual);
    }

    @Test
    void ocr2() throws TesseractException {
        tesseract = new Tesseract();
        tesseract.setDatapath(Config.getProperty("TESSETACT_DATAPATH"));
        File file = new File("src/test/resources/Resume.pdf");
        String actual = tesseract.doOCR(file);
        System.out.println(actual);
    }
}
