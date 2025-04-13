package org.example.controller;

import org.example.model.enums.StickerStatus;
import org.example.model.enums.TaskStatus;
import org.example.model.models.Sticker;
import org.example.model.models.Task;

import java.util.UUID;

public interface Manager {

    void addTask(UUID stickerID, String taskName);
    void addSticker(String stickerName);
    void deleteTask(UUID stickerID, UUID taskID);
    void deleteSticker(UUID stickerID);
    void changeTaskStatus(UUID stickerID, UUID taskID, TaskStatus taskStatus);
    void changeStickerStatus(UUID stickerID, StickerStatus stickerStatus);
    void showAll();
}
