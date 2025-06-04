package org.example.utils;

import org.example.model.models.Entity;

import java.io.*;

public class WriteFile {

    public static void writeDataInFile(Entity data, String path) {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        BufferedWriter bw = new BufferedWriter(outputStreamWriter)) {
            bw.write(data.toString());
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    private WriteFile() {}
}
