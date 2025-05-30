package org.example.serviseces;

import org.example.model.enums.StickerStatus;
import org.example.model.exeptions.ObjectNotFound;
import org.example.model.history_manager.HistoryManager;
import org.example.model.models.Sticker;
import org.example.model.repository.Repository;
import org.example.utils.IdChecker;
import org.example.utils.IdGenerator;
import java.util.List;
import java.util.UUID;

public class StickerService implements StickerServiceInterface{
    private Repository repository;
    private HistoryManager historyManager;

    public StickerService(Repository repository, HistoryManager historyManager) {
        this.repository = repository;
        this.historyManager = historyManager;
    }

    @Override
    public void add(String stickerName) {
        if (stickerName.isBlank()) {
            System.out.println("The sticker name is empty!");
            return;
        }
        List<Sticker> data = repository.getStickers();
        UUID uuid;
        do {
            uuid = IdGenerator.generateID();
        } while (IdChecker.isElementExist(data, uuid, Sticker.class));
        Sticker sticker = new Sticker(stickerName, uuid);
        System.out.println(sticker);
        repository.addSticker(sticker);
    }

    @Override
    public void delete(UUID stickerID) throws ObjectNotFound {
        Sticker sticker = getSticker(stickerID);
        repository.deleteSticker(sticker);
    }

    @Override
    public Sticker getSticker(UUID stickerID) throws ObjectNotFound{
        List<Sticker> data = repository.getStickers();
        Sticker sticker = IdChecker.getElementById(data, stickerID, Sticker.class);
        historyManager.add(sticker);
        return sticker;
    }

    @Override
    public List<Sticker> getAllStickers() {
        List<Sticker> data = repository.getStickers();
        return data;
    }

    @Override
    public void changeStatus(StickerStatus status, UUID stickerID) throws ObjectNotFound{
        Sticker sticker = getSticker(stickerID);
        sticker.changeStatus(status);
    }
}
