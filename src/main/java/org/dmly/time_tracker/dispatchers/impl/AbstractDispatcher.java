package org.dmly.time_tracker.dispatchers.impl;

import org.dmly.time_tracker.dispatchers.Dispatcher;

import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

public abstract class AbstractDispatcher implements Dispatcher {
    protected final Path configFilePath;
    protected final List<String> cmdArguments;
    protected final Properties properties;


    protected AbstractDispatcher(final Path configFilePath, final List<String> cmdArguments, final Properties properties) {
        this.configFilePath = configFilePath;
        this.cmdArguments = cmdArguments;
        this.properties = properties;
    }
}
