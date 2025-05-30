package org.example.controller.store_manager;

import org.example.model.enums.StickerStatus;
import org.example.model.enums.TaskStatus;
import org.example.model.exeptions.ObjectNotFound;

import java.util.UUID;

public interface Manager {

    void addTask(UUID stickerID, String taskName) throws ObjectNotFound;
    void addSticker(String stickerName);
    void deleteTask(UUID stickerID, UUID taskID) throws ObjectNotFound;
    void deleteSticker(UUID stickerID) throws ObjectNotFound;
    void changeTaskStatus(UUID stickerID, UUID taskID) throws ObjectNotFound;
    void changeStickerStatus(UUID stickerID, StickerStatus stickerStatus) throws ObjectNotFound;
    void showAll() throws ObjectNotFound;
}
