package org.dmly.time_tracker;

import org.dmly.time_tracker.constants.Constants;
import org.dmly.time_tracker.dispatchers.Dispatcher;
import org.dmly.time_tracker.dispatchers.impl.ConfigModeDispatcher;
import org.dmly.time_tracker.dispatchers.impl.TrackingModeDispatcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Main {

    private final Path configFilePath;
    private final List<String> cmdArguments;

    private Properties properties = new Properties();


    public static void main(String[] args) {
        new Main(args).start();
    }

    public Main(String[] args) {
        configFilePath = Paths.get(System.getProperty("user.dir"), Constants.CONFIG_FILE_NAME).toAbsolutePath();
        cmdArguments = Arrays.stream(args).collect(Collectors.toList());
    }

    public void start() {
        if (!Files.exists(configFilePath)) {
            System.out.println("Configuration file not found. Creating...");
            try {
                Files.createFile(configFilePath);
                Files.write(configFilePath, ("#Time tracker props.\n" + Constants.INTERVAL_PROPERTY_NAME + "=10m").getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Cannot create new configuration file.", e);
            }
        }

        try {
            readPropertiesFromFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read properties.", e);
        }

        createDispatcher().dispatch();

    }

    private void readPropertiesFromFile() throws IOException {
        FileInputStream inputStream = new FileInputStream(configFilePath.toFile());
        properties.load(inputStream);
    }

    private Dispatcher createDispatcher() {
        if (cmdArguments.size() > 0 && cmdArguments.get(0).equals(Constants.CONFIG_MODE_CMD_KEY)) {
            return new ConfigModeDispatcher(configFilePath, cmdArguments, properties);
        }
        return new TrackingModeDispatcher(configFilePath, cmdArguments, properties);
    }

}
