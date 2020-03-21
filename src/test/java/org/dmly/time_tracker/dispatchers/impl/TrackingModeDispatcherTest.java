package org.dmly.time_tracker.dispatchers.impl;

import org.dmly.time_tracker.dispatchers.Dispatcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class TrackingModeDispatcherTest {

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
        dispatcher = null;
    }

    @Test
    void nullProperties() {
        dispatcher = new TrackingModeDispatcher(configFilePath, cmdArguments, properties);

        Assertions.assertThrows(RuntimeException.class, () -> dispatcher.dispatch());
    }

    @Test
    void intervalNotSet() {
        properties = new Properties();
        dispatcher = new TrackingModeDispatcher(configFilePath, cmdArguments, properties);
    }
}