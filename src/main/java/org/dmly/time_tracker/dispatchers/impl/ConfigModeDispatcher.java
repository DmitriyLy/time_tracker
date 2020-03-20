package org.dmly.time_tracker.dispatchers.impl;

import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

public class ConfigModeDispatcher extends AbstractDispatcher {
    public ConfigModeDispatcher(final Path configFilePath, final List<String> cmdArguments, final Properties properties) {
        super(configFilePath, cmdArguments, properties);
    }

    @Override
    public void dispatch() {
        System.out.println("Config mode");
    }
}
