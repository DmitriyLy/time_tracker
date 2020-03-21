package org.dmly.time_tracker.dispatchers.impl;

import org.dmly.time_tracker.constants.Constants;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class TrackingModeDispatcher extends AbstractDispatcher {
    public TrackingModeDispatcher(final Path configFilePath, final List<String> cmdArguments, final Properties properties) {
        super(configFilePath, cmdArguments, properties);
    }

    @Override
    public void dispatch() {

        if (Objects.isNull(properties)) {
            throw new RuntimeException("Properties are null");
        }

        if (!properties.containsKey(Constants.CONFIG_FILE_NAME)) {
            System.out.println("\nInterval is not set.\n Exiting....");
            return;
        }

    }
}
