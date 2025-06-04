package org.example.model.repository;

import org.example.model.models.Sticker;
import org.example.model.models.Task;
import org.example.utils.AbsolutePathFinder;

import java.util.List;

public class FileRepository implements Repository {
    private String path;

    public FileRepository() {
        this.path = AbsolutePathFinder.absolutePathToDesktop();
    }

    @Override
    public List<Sticker> getStickers() {
        return List.of();
    }

    @Override
    public List<Task> getTasksById(Sticker sticker) {
        return List.of();
    }

    @Override
    public void addTask(Sticker sticker, Task task) {

    }

    @Override
    public void addSticker(Sticker sticker) {

    }

    @Override
    public void deleteTask(Sticker sticker, Task task) {

    }

    @Override
    public void deleteSticker(Sticker sticker) {

    }
}
