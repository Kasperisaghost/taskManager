package org.example.utils;

import org.example.model.repository.FileRepository;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {

    public static String readFile(String absolutePath) {
        StringBuilder sb = new StringBuilder();

        try (InputStreamReader inputStreamReader = new InputStreamReader(
                new FileInputStream(absolutePath), "UTF-8");
             BufferedReader bf = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = bf.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException io) {
            System.out.println(io);
        }

        return sb.toString();
    }
    }