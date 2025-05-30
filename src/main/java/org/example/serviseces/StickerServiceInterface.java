package org.example.serviseces;

import org.example.model.enums.StickerStatus;
import org.example.model.exeptions.ObjectNotFound;
import org.example.model.models.Sticker;

import java.util.List;
import java.util.UUID;

public interface StickerServiceInterface {
    void add(String stickerName);
    void delete(UUID stickerID) throws ObjectNotFound;
    Sticker getSticker(UUID stickerID) throws ObjectNotFound;
    void changeStatus(StickerStatus status, UUID stickerID) throws ObjectNotFound;
    List<Sticker> getAllStickers();
}
