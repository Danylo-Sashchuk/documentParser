package com.sashchuk.documentparser.util;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Settings {
    public static final String TESSETACT_DATAPATH = "/opt/homebrew/Cellar/tesseract/5.3.1/share/tessdata";
    public static final String VM_ARGUMENTS = "-Djna.library.path=/opt/homebrew/Cellar/tesseract/5.3.1/lib";


    private static Settings instance;
    private static Properties propertiesInstance;

    private Settings() {
        this("src/main/resources");
    }

    private Settings(String settingsPath) {
        propertiesInstance = new Properties();
        Path path = Paths.get(Path.of(settingsPath, "settings").toUri());
        System.out.println(path);
        System.out.println("break");
    }

    public static String getProperty(String key) {
        return propertiesInstance.getProperty(key);
    }

    private static void setProperty(String key, String value) {
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
