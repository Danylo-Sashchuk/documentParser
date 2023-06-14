package com.sashchuk.documentparser.tesscore;

import com.recognition.software.jdeskew.ImageDeskew;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.ImageHelper;

import java.awt.image.BufferedImage;
//TODO documentation

//https://tesseract-ocr.github.io/tessdoc/ImproveQuality.html
public class TessWrapper {
    public static String parse() {
        Tesseract tesseract = new Tesseract();

        return null;
    }

    public static BufferedImage deskewImage(BufferedImage image) {
        ImageDeskew id = new ImageDeskew(image);
        double imageSkewAngle = id.getSkewAngle(); // determine skew angle
        if ((imageSkewAngle > 0.05d || imageSkewAngle < -(0.05d))) {
            image = ImageHelper.rotateImage(image, -imageSkewAngle); // deskew image
        }
        return image;
    }
}
