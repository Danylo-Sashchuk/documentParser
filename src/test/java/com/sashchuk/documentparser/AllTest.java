package com.sashchuk.documentparser;

import com.sashchuk.documentparser.tesseract.TesseractTest;
import com.sashchuk.documentparser.util.ConfigTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ConfigTest.class, TesseractTest.class})
public class AllTest {

}
