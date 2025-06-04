package org.example;

import org.example.model.models.Sticker;
import org.example.model.models.Task;
import org.example.utils.AbsolutePathFinder;
import org.example.utils.ReadFile;
import org.example.utils.WriteFile;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //AppRunner.run();

        WriteFile.writeDataInFile(new Sticker("Sticker1", UUID.randomUUID()), AbsolutePathFinder.absolutePathToDesktop("some.csv"));
        WriteFile.writeDataInFile(new Task("Task1", UUID.randomUUID()), AbsolutePathFinder.absolutePathToDesktop("some.csv"));
        WriteFile.writeDataInFile(new Task("Task2", UUID.randomUUID()), AbsolutePathFinder.absolutePathToDesktop("some.csv"));


    }


}