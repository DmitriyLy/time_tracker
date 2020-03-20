package org.dmly.time_tracker;

import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("It works!!!");
        Arrays.stream(args).forEach(System.out::println);
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println(Paths.get("").toAbsolutePath().toString());
    }
}
