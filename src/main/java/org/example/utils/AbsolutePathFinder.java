package org.example.utils;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AbsolutePathFinder {
    public static final String BASIC_FILE_NAME = "unknow.txt";

    public static String absolutePathToDesktop(String filename) {
        String absolutePath = new File("config.properties").getAbsolutePath();
        String[] splitetString = absolutePath.split("/");
        //The range from 0 to 2 is steps for concat to new path.
        String sb = IntStream.rangeClosed(0, 2).mapToObj(x -> splitetString[x]+"/").collect(Collectors.joining()).trim();
        return sb + "Desktop/" + filename;
    }
    public static String absolutePathToDesktop() {
        String absolutePath = new File("config.properties").getAbsolutePath();
        String[] splitetString = absolutePath.split("/");
        //The range from 0 to 2 is steps for concat to new path.
        String sb = IntStream.rangeClosed(0, 2).mapToObj(x -> splitetString[x]+"/").collect(Collectors.joining()).trim();
        return sb + "Desktop/" + BASIC_FILE_NAME;
    }
}
