package org.example.model.history_manager;

import org.example.model.models.Entity;

public interface HistoryManager {
    public static int MAX_LIST_LENGTH = 10;

    void add(Entity entity);
    void showAll();
}
