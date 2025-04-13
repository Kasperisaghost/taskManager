package org.example.controller;

import org.example.model.enums.StickerStatus;
import org.example.model.enums.TaskStatus;
import org.example.model.exeptions.ObjectNotFound;
import org.example.model.models.Sticker;
import org.example.model.models.Task;
import org.example.model.repository.Repository;
import org.example.model.utils.IdChecker;
import org.example.model.utils.IdGenerator;
import org.example.model.utils.PropertiesReader;

import java.util.*;

public class InMemoryManager implements Manager {
    private Repository repository;
    private final String unknownName = PropertiesReader.getProperty("empty.string");


    public InMemoryManager(Repository globalRepository) {
        this.repository = globalRepository;
    }

    @Override
    public void addTask(UUID stickerID, String taskName) {
        if (taskName.isBlank()) taskName = unknownName;
        Task task = new Task(taskName, IdGenerator.generateID());;

        try {
            List<Task> data = getSafeTasks(stickerID);
            data.add(task);
        } catch (ObjectNotFound o) {
            System.out.println(o);
        }
    }

    @Override
    public void addSticker(String stickerName) {
        if (stickerName.isBlank()) stickerName = unknownName;

        UUID uuid;
        do {
            uuid = UUID.randomUUID();
        } while (IdChecker.isIdInCollection(repository.getStickers(), uuid));

        Sticker sticker = new Sticker(stickerName, uuid);
        repository.addSticker(sticker);
    }

    @Override
    public void deleteTask(UUID stickerID, UUID taskID) {
        try {
            List<Task> data = getSafeTasks(stickerID);
            if (data.isEmpty()) {
                System.out.println("This sticker has nothing!");
                return;
            }

            Task task = IdChecker.getElementById(data, taskID, Task.class);
            Sticker sticker = IdChecker.getElementById(repository.getStickers(), stickerID, Sticker.class);
            repository.deleteTask(sticker, task);

            stickerStatusChanger(data, stickerID);

        } catch (ObjectNotFound o) {
            System.out.println(o);
        }
    }

    @Override
    public void deleteSticker(UUID stickerID) {
        try {
            Sticker sticker = IdChecker.getElementById(repository.getStickers(), stickerID, Sticker.class);
            repository.deleteSticker(sticker);

        } catch (ObjectNotFound o) {
            System.out.println(o);
        }
    }

    @Override
    public void changeTaskStatus(UUID stickerID, UUID taskID, TaskStatus taskStatus) {
        try{
            List<Task> data = getSafeTasks(stickerID);
            Task currentTask = IdChecker.getElementById(data, taskID, Task.class);
            currentTask.changeStatus(taskStatus);
            changeStickerStatus(stickerID, StickerStatus.IN_PROGRESS);

            stickerStatusChanger(data, stickerID);


        } catch (ObjectNotFound o) {
            System.out.println(o);
        }
    }

    @Override
    public void changeStickerStatus(UUID stickerID, StickerStatus stickerStatus) {
        Set<Sticker> stickers = repository.getStickers();
        if (stickers.isEmpty()) {
            System.out.println("No one sticker in collection");
            return;
        }
        try {
            Sticker sticker = IdChecker.getElementById(stickers, stickerID, Sticker.class);
            sticker.changeStatus(stickerStatus);

        } catch (ObjectNotFound o) {
            System.out.println(o);
        }
    }

    public void showAll() {
        Set<Sticker> stickers = repository.getStickers();
        if (stickers.isEmpty()) {
            System.out.println("No one sticker is here");
            return;
        }

        for (Sticker sticker: stickers) {
            System.out.println(sticker);
            List<Task> tasks = repository.getTasksById(sticker);
            tasks.forEach(System.out::println);
        }
    }

    private List<Task> getSafeTasks(UUID stickerId) throws ObjectNotFound {
        Sticker sticker = IdChecker.getElementById(repository.getStickers(), stickerId, Sticker.class);
        return repository.getTasksById(sticker);
    }

    private void stickerStatusChanger(List<Task> data, UUID stickerID) {

        int numberOfEndedTasks = (int) data.stream()
                .filter(x -> Objects.equals(x.getStatus(), TaskStatus.FINISHED))
                .count();

        if (numberOfEndedTasks == data.size()) {
            changeStickerStatus(stickerID, StickerStatus.DONE);
            return;
        }
        changeStickerStatus(stickerID, StickerStatus.IN_PROGRESS);
    }
}
