package org.example.controller.store_manager;

import org.example.model.enums.StickerStatus;
import org.example.model.enums.TaskStatus;
import org.example.model.exeptions.ObjectNotFound;
import org.example.model.models.Sticker;
import org.example.model.models.Task;
import org.example.serviseces.StickerServiceInterface;
import org.example.serviseces.TaskServiceInterface;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class InMemoryManager implements Manager {

    private final TaskServiceInterface taskServiceInterface;
    private final StickerServiceInterface stickerServiceInterface;

    public InMemoryManager(TaskServiceInterface taskServiceInterface, StickerServiceInterface stickerServiceInterface) {
        this.stickerServiceInterface = stickerServiceInterface;
        this.taskServiceInterface = taskServiceInterface;
    }


    @Override
    public void addTask(UUID stickerID, String taskName) throws ObjectNotFound {
        Sticker sticker = stickerServiceInterface.getSticker(stickerID);
        taskServiceInterface.add(sticker, taskName);
    }

    @Override
    public void addSticker(String stickerName) {
        stickerServiceInterface.add(stickerName);
    }

    @Override
    public void deleteTask(UUID stickerID, UUID taskID) throws ObjectNotFound {
        Sticker sticker = stickerServiceInterface.getSticker(stickerID);
        taskServiceInterface.delete(sticker, taskID);
    }

    @Override
    public void deleteSticker(UUID stickerID) throws ObjectNotFound {
        stickerServiceInterface.delete(stickerID);
    }

    @Override
    public void changeTaskStatus(UUID stickerID, UUID taskID) throws ObjectNotFound {
        Sticker sticker = stickerServiceInterface.getSticker(stickerID);
        taskServiceInterface.changeStatus(sticker, taskID);
        if (isAllTaskComplete(sticker)) {
            changeStickerStatus(stickerID, StickerStatus.DONE);
            return;
        }
        changeStickerStatus(stickerID, StickerStatus.IN_PROGRESS);
    }

    @Override
    public void changeStickerStatus(UUID stickerID, StickerStatus stickerStatus) throws ObjectNotFound {
        stickerServiceInterface.changeStatus(stickerStatus, stickerID);
    }

    @Override
    public void showAll() throws ObjectNotFound {
        List<Sticker> stickers = stickerServiceInterface.getAllStickers();
        for (Sticker sticker: stickers) {
            System.out.println(sticker + "\n");
            for (Task task: taskServiceInterface.getAllTasks(sticker)) {
                System.out.print(task + "\t");
            }
            System.out.println("\n");
        }
    }

    private boolean isAllTaskComplete(Sticker sticker) throws ObjectNotFound {
        List<Task> data = taskServiceInterface.getAllTasks(sticker);
        return data.stream().allMatch(x -> Objects.equals(x.getStatus(), TaskStatus.FINISHED));
    }



}



