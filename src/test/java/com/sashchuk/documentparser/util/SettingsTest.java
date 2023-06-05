package com.sashchuk.documentparser.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;


class SettingsTest {

    public static final String TEST_SETTINGS_PATH = "src/test/resources";


    static Stream<Arguments> getProperties() {
        return Stream.of(arguments("ENABLE_FEATURE_A", "true"), arguments("ENABLE_FEATURE_B", "false"), arguments(
                "API_KEY", "1234567890"), arguments("DATABASE_HOST", "localhost"), arguments("DATABASE_PORT", "5432")
                , arguments("DATABASE_USERNAME", "myuser"), arguments("DATABASE_PASSWORD", "mypassword"));
    }

    @BeforeAll
    static void beforeAll() {
        Settings.getInstance(TEST_SETTINGS_PATH);
    }

    @ParameterizedTest
    @MethodSource
    void getProperties(String key, String value) {
        Assertions.assertThat(value)
                .isEqualTo(Settings.getProperty(key));
    }
}