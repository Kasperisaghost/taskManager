package org.example.model.models;

import org.example.model.enums.TaskStatus;

import java.util.Objects;
import java.util.UUID;

public final class Task extends Entity {
    private TaskStatus status;

    public Task(String task, UUID id) {
        super(task, id);
        this.status = TaskStatus.IN_PROGRESS;
    }

    public TaskStatus getStatus() {
        return status;
    }
    public void changeStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        return Objects.equals(super.getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.getId());
    }

    @Override
    public String toString() {
        return "\nStatus "
                + status
                + "\nTask name: "
                + super.getTitle()
                + "\nID: "
                + super.getId()
                + "\n";
    }
}
