package org.example.serviseces;

import org.example.model.enums.TaskStatus;
import org.example.model.exeptions.ObjectNotFound;
import org.example.model.models.Sticker;
import org.example.model.models.Task;

import java.util.List;
import java.util.UUID;

public interface TaskServiceInterface {
    void add(Sticker sticker, String title);
    void delete(Sticker sticker, UUID taskID) throws ObjectNotFound;
    Task getTask(Sticker sticker, UUID taskID) throws ObjectNotFound;
    void changeStatus(Sticker sticker, UUID taskID) throws ObjectNotFound;
    List<Task> getAllTasks(Sticker sticker) throws ObjectNotFound;
}
