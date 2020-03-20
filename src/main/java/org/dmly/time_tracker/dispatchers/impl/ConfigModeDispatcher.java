package org.dmly.time_tracker.dispatchers.impl;

import org.dmly.time_tracker.constants.Constants;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

public class ConfigModeDispatcher extends AbstractDispatcher {
    public ConfigModeDispatcher(final Path configFilePath, final List<String> cmdArguments, final Properties properties) {
        super(configFilePath, cmdArguments, properties);
    }

    @Override
    public void dispatch() {
        if (cmdArguments.size() > 1 && cmdArguments.get(1) .equals(Constants.SET_PROPERTY_CMD_KEY)) {
            addProperty();
            saveProperties();
        }
    }

    private void addProperty() {
        if (cmdArguments.size() >= 4) {
            properties.put(cmdArguments.get(2), cmdArguments.get(3));
        } else {
            throw new RuntimeException("Properties not found");
        }
    }

    private void saveProperties() {
        try {
            FileOutputStream outputStream = new FileOutputStream(configFilePath.toFile());
            properties.store(outputStream, "");
        } catch (IOException e) {
            throw new RuntimeException("Cannot save properties.", e);
        }
    }
}
