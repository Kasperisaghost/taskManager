package org.example.model.history_manager;

import org.example.model.models.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InMemoryHistoryManager implements HistoryManager{
    private List<Entity> history;


    public InMemoryHistoryManager() {
        this.history = new ArrayList<>();
    }

    @Override
    public void add(Entity entity) {
        // ["a", null, null] add("b") -> ["b", "a", null]
        if (entity != null) {
            history.add(entity);
            return;
        }
        System.out.println("The field is empty");
    }

    @Override
    public void showAll() {
        int firstElement = Math.max(history.size() - HistoryManager.MAX_LIST_LENGTH, 0);
        IntStream.rangeClosed(firstElement, firstElement+10).mapToObj(x -> history.get(x)).forEach(System.out::println);
    }
}
