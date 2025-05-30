package org.example;

import org.example.model.models.Sticker;
import org.example.model.models.Task;
import org.example.model.repository.GlobalRepository;
import org.example.model.repository.Repository;
import org.example.utils.IdGenerator;
import org.example.utils.UserLineReader;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AppRunner.run();
        UUID uuid = UUID.randomUUID();
        String st = String.valueOf(uuid);
        System.out.println(Objects.equals(uuid, UUID.fromString(st)));



    }


}