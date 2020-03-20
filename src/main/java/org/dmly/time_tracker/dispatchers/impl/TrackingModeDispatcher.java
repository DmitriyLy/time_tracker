package org.dmly.time_tracker.dispatchers.impl;

import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

public class TrackingModeDispatcher extends AbstractDispatcher {
    public TrackingModeDispatcher(final Path configFilePath, final List<String> cmdArguments, final Properties properties) {
        super(configFilePath, cmdArguments, properties);
    }

    @Override
    public void dispatch() {
        System.out.println("Tracking Mode");
    }
}
