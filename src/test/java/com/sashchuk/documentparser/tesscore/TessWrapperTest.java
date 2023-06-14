package com.sashchuk.documentparser.tesscore;

import com.sashchuk.documentparser.util.Config;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class TessWrapperTest {
    private final Tesseract tesseract = new Tesseract();

    @BeforeAll
    static void beforeAll() {
        Config.recreate();
        System.setProperty("jna.library.path", Config.getProperty("JNA_LIBRARY_PATH"));
    }

    @Test
    void testDoOCR_SkewedImage() throws IOException, TesseractException {
        tesseract.setDatapath(Config.getProperty("TESSETACT_DATAPATH"));
        File file = new File("src/test/resources/skewedImage.png");
        BufferedImage bi = ImageIO.read(file);
        bi = TessWrapper.deskewImage(bi);

        String expected = """
                The (quick) [brown] {fox} jumps!
                Over the $43,456.78 <lazy> #90 dog
                & duck/goose, as 12.5% of E-mail
                from aspammer@website.com is spam.
                Der ,.schnelle” braune Fuchs springt
                iiber den faulen Hund. Le renard brun
                «rapide» saute par-dessus le chien
                paresseux. La volpe marrone rapida
                salta sopra il cane pigro. El zorro
                marron rapido salta sobre el perro
                perezoso. A raposa marrom rapida
                salta sobre o cao preguicoso.
                """;
        String actual = tesseract.doOCR(bi);
        Assertions.assertThat(actual)
                .isEqualTo(expected);
    }
}