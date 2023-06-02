package com.sashchuk.documentparser.tesseract;

import com.sashchuk.documentparser.util.Settings;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class TesseractTest {

    Tesseract tesseract;

    @BeforeEach
    void setUp() {

    }

    @Test
    void ocr() throws TesseractException {
        tesseract = new Tesseract();
        tesseract.setDatapath(Settings.getProperty("TESSETACT_DATAPATH"));
        System.setProperty("jna.library.path", Settings.getProperty("JNA_LIBRARY_PATH"));
        System.out.println(System.getProperty("jna.library.path"));

        File file = new File("/Users/Danylo/Desktop/Workspace/documentparser/src/test/resources/ProcessEssay.pdf");
        String expected = "Danylo Sashchuk\n" + "Professor Jose Macia\n" + "ENC1101\n" + "30 May 2023\n" + "How to Write an Essay\n" + "\tHave you ever struggled with essay writing? I am sure you have, and this is normal. Essay writing is a complex and comprehensive process requiring much time, concentration, and knowledge. Despite the complexity and difficulty, virtually every college class requires at least one essay for the semester, while university degrees demand immense and elaborated articles. So what can you do to successfully write an essay? Do not be afraid because by following these simple steps, you can compose an excellent paper that will get you a higher grade!\n" + "\tThe initial step to do before any actual writing process is to research and plan your essay. This stage consists of several steps. First, you must understand your essay's topic thoroughly and do research. Sometimes, the provided assignment's topic might be confusing or unclear. It could result in any of the following writing activities to be inherently wrong or useless, so make sure to ask and determine the topic correctly. Next, do a little initial research to find the ideas you will put in your essay; however, remember to save and cite these sources to avoid accidental plagiarism. Second, after you discern the topic and what you will write, you must determine the writing mode. There are many of them, and you should think about the chosen one for a while because inappropriate writing modes may threaten to annihilate all of your worthwhile efforts, and the reader’s engagement will be hindered. In addition, take into consideration your target audience. Sometimes, adding slang language or jokes can be beneficial for your essay when it is written for a specific group of people, while another audience may not tolerate that. Now, based ";
        String actual = tesseract.doOCR(file);
        expected = expected.replace("\n", " ").replace("\r", " ");
        actual = actual.replace("\n", "").replace("\r", "");
        Assertions.assertThat(expected).isEqualTo(actual);
    }
}
