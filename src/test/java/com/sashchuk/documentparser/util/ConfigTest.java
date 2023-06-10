package com.sashchuk.documentparser.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;


class ConfigTest {

    public static final String TEST_SETTINGS_PATH = "src/test/resources";

    static Stream<Arguments> getTestProperties() {
        return Stream.of(arguments("ENABLE_FEATURE_A", "true"), arguments("ENABLE_FEATURE_B", "false"), arguments(
                "API_KEY", "1234567890"), arguments("DATABASE_HOST", "localhost"), arguments("DATABASE_PORT", "5432")
                , arguments("DATABASE_USERNAME", "myuser"), arguments("DATABASE_PASSWORD", "mypassword"));
    }

    @BeforeAll
    static void beforeAll() {
        Config.recreate(TEST_SETTINGS_PATH);
    }

    @ParameterizedTest
    @MethodSource
    void getTestProperties(String key, String value) {
        Assertions.assertThat(value)
                .isEqualTo(Config.getProperty(key));
    }

    @Test
    void getProperties_whenKeyDoesNotExist_thenThrowException() {
        Assertions.assertThatThrownBy(() -> Config.getProperty("NON_EXISTENT_KEY"))
                .isInstanceOf(ConfigException.class);
    }

    @Test
    void recreate_whenLoadingFails_thenThrowException() {
        Assertions.assertThatThrownBy(() -> Config.recreate("NON_EXISTENT_PATH"))
                .isInstanceOf(ConfigException.class);
    }

    @Test
    void setProperty() {
        Config.setProperty("NEW_KEY", "NEW_VALUE");
        Assertions.assertThat(Config.getProperty("NEW_KEY"))
                .isEqualTo("NEW_VALUE");
    }

    @Test
    void setProperty_whenPropertyAlreadyExists_thenThrowException() {
        Assertions.assertThatThrownBy(() -> Config.setProperty("ENABLE_FEATURE_A", "false"))
                .isInstanceOf(ConfigException.class);
    }

    @Test
    void getProperties() {
        Map<String, String> expected = new HashMap<>();
        expected.put("ENABLE_FEATURE_A", "true");
        expected.put("ENABLE_FEATURE_B", "false");
        expected.put("API_KEY", "1234567890");
        expected.put("DATABASE_HOST", "localhost");
        expected.put("DATABASE_PORT", "5432");
        expected.put("DATABASE_USERNAME", "myuser");
        expected.put("DATABASE_PASSWORD", "mypassword");


        Assertions.assertThat(Config.getProperties())
                .hasSize(7);
        Assertions.assertThat(Config.getProperties())
                .isEqualTo(expected);
    }

    @Test
    void recreate() {
        Config.recreate(TEST_SETTINGS_PATH);
        Assertions.assertThat(Config.getProperties())
                .hasSize(7);
    }
}