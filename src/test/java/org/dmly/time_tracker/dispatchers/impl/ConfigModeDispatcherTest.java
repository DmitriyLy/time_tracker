package org.dmly.time_tracker.dispatchers.impl;

import org.dmly.time_tracker.constants.Constants;
import org.dmly.time_tracker.dispatchers.Dispatcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ConfigModeDispatcherTest {

    private Properties properties;
    private Path configFilePath;
    private List<String> cmdArguments;

    private Dispatcher dispatcher;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        properties = null;
        cmdArguments = null;
        configFilePath = null;
    }

    @Test
    void dispatch_emptyCommandList() {
        properties = new Properties();
        cmdArguments = new ArrayList<>();
        configFilePath = Paths.get("", Constants.CONFIG_FILE_NAME);

        dispatcher = new ConfigModeDispatcher(configFilePath, cmdArguments, properties);
        dispatcher.dispatch();

        assertEquals(0, properties.size());
    }

    @Test
    void dispatch_nullCommandList() {
        properties = new Properties();
        configFilePath = Paths.get("", Constants.CONFIG_FILE_NAME);

        dispatcher = new ConfigModeDispatcher(configFilePath, cmdArguments, properties);

        Assertions.assertThrows(RuntimeException.class, () -> dispatcher.dispatch());

        assertEquals(0, properties.size());
    }

    @Test
    void dispatch_nullProperties() {
        configFilePath = Paths.get("", Constants.CONFIG_FILE_NAME);
        dispatcher = new ConfigModeDispatcher(configFilePath, cmdArguments, properties);

        Assertions.assertThrows(RuntimeException.class, () -> dispatcher.dispatch());
    }

    @Test
    void dispatch_nullConfigFilePath() {
        dispatcher = new ConfigModeDispatcher(configFilePath, cmdArguments, properties);

        Assertions.assertThrows(RuntimeException.class, () -> dispatcher.dispatch());
    }


    @Test
    void dispatch_successPropertiesWriting() {
        properties = new Properties();
        configFilePath = Paths.get(System.getProperty("user.dir"), "target", "tmp",  Constants.CONFIG_FILE_NAME);

        cmdArguments = Stream.of(Constants.CONFIG_MODE_CMD_KEY, Constants.SET_PROPERTY_CMD_KEY, "test.prop", "test.value")
                .collect(Collectors.toList());

        try {

            if (Files.exists(configFilePath)) {
                Files.delete(configFilePath);
            }

            Files.createDirectories(Paths.get(System.getProperty("user.dir"), "target", "tmp"));
            Files.createFile(configFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }


        dispatcher = new ConfigModeDispatcher(configFilePath, cmdArguments, properties);
        dispatcher.dispatch();

        Assertions.assertEquals(1, properties.size());
        Assertions.assertTrue(properties.containsKey("test.prop"));
        Assertions.assertEquals("test.value", properties.getProperty("test.prop"));

        try {

            if (Files.exists(configFilePath)) {
                Files.delete(configFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }
}