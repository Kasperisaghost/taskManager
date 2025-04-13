package org.example.model.repository;

import org.example.model.models.Sticker;
import org.example.model.models.Task;

import java.util.*;

public class GlobalRepository implements Repository {
    private Map<Sticker, List<Task>> data;

    public GlobalRepository() {
        this.data = new HashMap<>();
    }


    @Override
    public Set<Sticker> getStickers() {
        return data.keySet();
    }

    @Override
    public List<Task> getTasksById(Sticker sticker) {
        return data.get(sticker);
    }

    @Override
    public void addTask(Sticker sticker, Task task) {
        List<Task> tasks = data.get(sticker);
        tasks.add(task);
    }

    @Override
    public void addSticker(Sticker sticker) {
        data.put(sticker, new ArrayList<>());
    }

    @Override
    public void deleteTask(Sticker sticker, Task task) {

        List<Task> tasks = data.get(sticker);
        tasks.remove(task);
    }

    @Override
    public void deleteSticker(Sticker sticker) {
        data.remove(sticker);
    }


}
