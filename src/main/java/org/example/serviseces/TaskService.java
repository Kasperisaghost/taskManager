package org.example.serviseces;

import org.example.model.enums.TaskStatus;
import org.example.model.exeptions.ObjectNotFound;
import org.example.model.history_manager.HistoryManager;
import org.example.model.history_manager.InMemoryHistoryManager;
import org.example.model.models.Sticker;
import org.example.model.models.Task;
import org.example.model.repository.Repository;
import org.example.utils.IdChecker;
import org.example.utils.IdGenerator;

import java.util.List;
import java.util.UUID;

public class TaskService implements TaskServiceInterface{
    private Repository repository;
    private HistoryManager historyManager;

    public TaskService(Repository repository, HistoryManager historyManager) {
        this.repository = repository;
        this.historyManager = historyManager;
    }

    @Override
    public void add(Sticker sticker, String title) {
        if (title.isBlank()) {
            System.out.println("Task can't be empty!");
            return;
        }
        Task task = new Task(title, IdGenerator.generateID());
        System.out.println(task);
        repository.addTask(sticker, task);
    }

    @Override
    public void delete(Sticker sticker, UUID taskID) throws ObjectNotFound {
        Task task = getTask(sticker, taskID);
        repository.deleteTask(sticker, task);
    }

    @Override
    public Task getTask(Sticker sticker, UUID taskID) throws ObjectNotFound {
        List<Task> data = repository.getTasksById(sticker);
        Task task = IdChecker.getElementById(data, taskID, Task.class);
        historyManager.add(task);
        return task;
    }

    @Override
    public List<Task> getAllTasks(Sticker sticker) {
        return repository.getTasksById(sticker);
    }

    @Override
    public void changeStatus(Sticker sticker, UUID taskID) throws ObjectNotFound {
        Task task = getTask(sticker, taskID);
        if (task.getStatus() == TaskStatus.IN_PROGRESS) {
            task.changeStatus(TaskStatus.FINISHED);
            return;
        }
        task.changeStatus(TaskStatus.IN_PROGRESS);
    }

}
