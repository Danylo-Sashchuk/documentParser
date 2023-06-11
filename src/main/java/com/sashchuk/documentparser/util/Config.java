package com.sashchuk.documentparser.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This is a config singleton that contains all the necessary configs through the {@link java.util.Properties} class.
 */

public class Config {
    public static final String DEFAULT_CONFIG_PATH = "src/main/resources/config.properties";
    private static final Config instance = new Config();
    private static Properties propertiesInstance;

    private Config() {
        this(DEFAULT_CONFIG_PATH);
    }

    private Config(String configPath) {
        loadConfig(configPath);
    }

    /**
     * @param key key of the property.
     * @return value of the property.
     * @throws ConfigException if property does not exist.
     */
    public static String getProperty(String key) {
        String property = propertiesInstance.getProperty(key);
        if (property == null) {
            throw new ConfigException("Property " + property + " does not exist");
        }
        return property;
    }

    /**
     * @param key   key of the property to store.
     * @param value value of the property to store.
     * @throws ConfigException if property already exists.
     */
    public static void setProperty(String key, String value) {
        if (propertiesInstance.containsKey(key)) {
            throw new ConfigException("Property " + key + " already exists");
        }
        propertiesInstance.setProperty(key, value);
    }

    /**
     * @return new HashMap&lt;String, String&gt; with all the properties of the config.
     */
    public static Map<String, String> getProperties() {
        Map<String, String> properties = new HashMap<>();
        propertiesInstance.forEach((key, value) -> properties.put((String) key, (String) value));
        return properties;
    }

    /**
     * Reloads the config.
     */
    public static void recreate() {
        loadConfig(DEFAULT_CONFIG_PATH);
    }

    /**
     * Reloads the config from the specified path.
     *
     * @param path path do the config's folder.
     */
    public static void recreate(String path) {
        loadConfig(path);
    }

    /**
     * Loads the config from the specified path.
     *
     * @param configPath path do the config's folder.
     * @throws ConfigException if exception occurs while loading the config file.
     */
    private static void loadConfig(String configPath) {
        propertiesInstance = new Properties();
        Path path = Paths.get(configPath);
        try {
            propertiesInstance.load(Files.newBufferedReader(path));
        } catch (IOException ioException) {
            throw new ConfigException("Config files has not been loaded", ioException);
        }
    }
}
