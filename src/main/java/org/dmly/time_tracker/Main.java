package org.dmly.time_tracker;

import org.dmly.time_tracker.constants.Constants;

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
    private final String workingDirectory;
    private final List<String> cmdArguments;

    private Properties properties = new Properties();


    public static void main(String[] args) {
        new Main(args).start();
    }

    public Main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        configFilePath = Paths.get(workingDirectory, Constants.CONFIG_FILE_NAME).toAbsolutePath();
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

        if (cmdArguments.size() > 0 && cmdArguments.get(0).equals(Constants.CONFIG_MODE_CMD_KEY)) {
            startConfigMode();
        } else {
            startTrackingMode();
        }

    }

    private void readPropertiesFromFile() throws IOException {
        FileInputStream inputStream = new FileInputStream(configFilePath.toFile());
        properties.load(inputStream);
        System.out.println(properties);
    }

    private void startConfigMode() {

    }

    private void startTrackingMode() {

    }

}
