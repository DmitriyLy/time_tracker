package org.dmly.time_tracker.dispatchers.impl;

import org.dmly.time_tracker.constants.Constants;
import org.dmly.time_tracker.dispatchers.Dispatcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ConfigModeDispatcherTest {

    private Properties properties;
    private Path configFilePath;
    private List<String> cmdArguments;

    private Dispatcher dispatcher;

    @BeforeEach
    void setUp() {
        configFilePath = Paths.get("", Constants.CONFIG_FILE_NAME);
    }

    @AfterEach
    void tearDown() {
        properties = null;
        cmdArguments = null;
    }

    @Test
    void dispatch_emptyCommandList() {
        properties = new Properties();
        cmdArguments = new ArrayList<>();

        dispatcher = new ConfigModeDispatcher(configFilePath, cmdArguments, properties);
        dispatcher.dispatch();

        assertEquals(0, properties.size());
    }

    @Test
    void dispatch_nullCommandList() {
        properties = new Properties();

        dispatcher = new ConfigModeDispatcher(configFilePath, cmdArguments, properties);

        Assertions.assertThrows(RuntimeException.class, () -> {
            dispatcher.dispatch();
        });

        assertEquals(0, properties.size());
    }
}