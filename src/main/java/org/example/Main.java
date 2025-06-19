package org.example;

import org.example.model.exeptions.ObjectNotFound;
import org.example.model.models.Sticker;
import org.example.model.models.Task;
import org.example.model.repository.GlobalRepository;
import org.example.model.repository.PostgressRepository;
import org.example.model.repository.Repository;
import org.example.utils.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ObjectNotFound {
        //AppRunner.run();
        Repository repository = PostgressRepository.getInstance();
        Sticker sticker = new Sticker("so",
                UUID.fromString("9b922d23-ad0b-4633-afe0-d28f6549a802"));
        repository.getTasksById(sticker).forEach(System.out::println);
    }
}