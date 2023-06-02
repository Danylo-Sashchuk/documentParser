package com.sashchuk.documentparser.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Settings {
    //public static final String TESSETACT_DATAPATH = "/opt/homebrew/Cellar/tesseract/5.3.1/share/tessdata";
//    public static final String VM_ARGUMENTS = "-Djna.library.path=/opt/homebrew/Cellar/tesseract/5.3.1/lib";


    private static Settings instance;
    private static Properties propertiesInstance;

    private Settings() {
        this("src/main/resources");
    }

    private Settings(String settingsPath) {
        propertiesInstance = new Properties();
        Path path = Paths.get(settingsPath, "settings");
        try {
            propertiesInstance.load(Files.newBufferedReader(path));
        } catch (IOException ioException) {
            throw new RuntimeException("settings files has not been read");
        }
    }

    public static String getProperty(String key) {
        getInstance();
        return propertiesInstance.getProperty(key);
    }

    private static void setProperty(String key, String value) {
        getInstance();
        propertiesInstance.setProperty(key, value);
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public static Settings getInstance(String settingsPath) {
        if (instance == null) {
            instance = new Settings(settingsPath);
        }
        return instance;
    }
}
