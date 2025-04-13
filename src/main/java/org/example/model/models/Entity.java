package org.example.model.models;

import java.util.UUID;

public abstract class Entity {
    private String title;
    private UUID id;

    public Entity(String title, UUID id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public UUID getId() {
        return id;
    }

    public void setTitle(String st) {
        this.title = title;
    }


}
