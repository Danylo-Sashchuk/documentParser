package com.sashchuk.documentparser.tesseract;

import com.sashchuk.documentparser.util.Settings;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TesseractTest {

    static final Tesseract tesseract = new Tesseract();

    @BeforeAll
    static void beforeAll() {
//        tesseract = new Tesseract();

    }

    @Test
    void ocr() throws TesseractException {
        tesseract.setDatapath(Settings.getProperty("TESSETACT_DATAPATH"));
        String pr = Settings.getProperty("JNA_LIBRARY_PATH");
        System.setProperty("jna.library.path", pr);
        System.out.println(System.getProperty("jna.library.path"));
        //Given
        File file = new File("src/test/resources/ProcessEssay.pdf");
        String expected = """
                Write Your Title Here
                After outlining about the topic “How to be a successful college student,” start your paragraph by indenting it. Then, continue with your topic sentence defis remember to include the topic “successful student” and the controlling idea “steps” in your topic sentence. After you write your TS, provide the first supporting detail, which should be the first important step. Then provide the two minors for your SD1. Remember to provide an example and explanation for each minor. Continue your paragraph following the structure learned in class.
                """;

        //When
        String actual = tesseract.doOCR(file);
        expected = expected.replace("\n", " ").replace("\r", " ").replace(" ", "");
        actual = actual.replace("\n", "").replace("\r", "").replace(" ", "");

        //Then
        Assertions.assertThat(expected).isEqualTo(actual);
    }
}
