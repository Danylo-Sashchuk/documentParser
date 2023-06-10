package com.sashchuk.documentparser.tesseract;

import com.sashchuk.documentparser.util.Config;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TesseractTest {

    static Tesseract tesseract;

    @BeforeAll
    static void beforeAll() {
        Config.recreate();
        tesseract = new Tesseract();
        tesseract.setDatapath(Config.getProperty("TESSETACT_DATAPATH"));
        System.setProperty("jna.library.path", Config.getProperty("JNA_LIBRARY_PATH"));
    }

    @Test
    void ocr() throws TesseractException {
        //Given
        File file = new File("src/test/resources/ProcessEssay.pdf");
        String expected = """
                Write Your Title Here
                After outlining about the topic “How to be a successful college student,” start your paragraph by indenting it. Then, continue with your topic sentence defis remember to include the topic “successful student” and the controlling idea “steps” in your topic sentence. After you write your TS, provide the first supporting detail, which should be the first important step. Then provide the two minors for your SD1. Remember to provide an example and explanation for each minor. Continue your paragraph following the structure learned in class.
                """;

        //When
        String actual = tesseract.doOCR(file);
        expected = expected.replace("\n", " ")
                .replace("\r", " ")
                .replace(" ", "");
        actual = actual.replace("\n", "")
                .replace("\r", "")
                .replace(" ", "");

        //Then
        Assertions.assertThat(expected)
                .isEqualTo(actual);
    }
}
