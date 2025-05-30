package org.example.model.repository;

import org.example.model.models.Sticker;
import org.example.model.models.Task;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface Repository {

    List<Sticker> getStickers();
    List<Task> getTasksById(Sticker sticker);

    void addTask(Sticker sticker, Task task);
    void addSticker(Sticker sticker);

    void deleteTask(Sticker sticker, Task task);
    void deleteSticker(Sticker sticker);

}
