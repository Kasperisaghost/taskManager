package org.example.model.models;

import org.example.model.enums.StickerStatus;

import java.util.Objects;
import java.util.UUID;


public final class Sticker extends Entity {
    private StickerStatus status;

    public Sticker(String name, UUID id) {
        super(name, id);
        this.status = StickerStatus.TO_DO;
    }

    public Sticker(String name, UUID id, StickerStatus stickerStatus) {
        super(name, id);
        this.status = stickerStatus;
    }

    public StickerStatus getStatus() {
        return status;
    }

    public void changeStatus(StickerStatus stickerStatus) {
        status = stickerStatus;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sticker sticker = (Sticker) o;
        return Objects.equals(getId(), sticker.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.getId());
    }

    @Override
    public String toString() {
        return "-> -> -> Sticker{" + "\n" +
                "id= " + super.getId() + "\n" +
                "title= " + super.getTitle() + "\n" +
                "status= " + status +
                '}' + "\n";
    }
}
