package org.example;

import org.example.controller.store_manager.InMemoryManager;
import org.example.controller.store_manager.Manager;
import org.example.model.enums.TypeStore;
import org.example.model.exeptions.BellowZero;
import org.example.model.exeptions.ObjectNotFound;
import org.example.model.history_manager.HistoryManager;
import org.example.model.history_manager.InMemoryHistoryManager;
import org.example.model.repository.GlobalRepository;
import org.example.model.repository.Repository;
import org.example.serviseces.StickerService;
import org.example.serviseces.StickerServiceInterface;
import org.example.serviseces.TaskService;
import org.example.serviseces.TaskServiceInterface;
import org.example.utils.StoreOptions;
import org.example.utils.UserLineReader;

import java.util.NoSuchElementException;
import java.util.UUID;

public class AppRunner {

    public static void run() {
        TypeStore typeStore = startInicializationProcess();
        Repository repository = getRepositoryByType(typeStore);

        HistoryManager historyManager = new InMemoryHistoryManager();

        StickerServiceInterface stickerServiceInterface = new StickerService(repository, historyManager);
        TaskServiceInterface taskServiceInterface = new TaskService(repository, historyManager);
        Manager manager = new InMemoryManager(taskServiceInterface, stickerServiceInterface);

        while (true) {
            System.out.println("Enter a function!(Enter by digit)");
            System.out.println("1. Add sticker.\n" +
                    "2. Add task.\n" +
                    "3. Show all.\n" +
                    "4. Delete sticker.\n" +
                    "5. Delete task.\n" +
                    "6. Change task status.\n" +
                    "0. Exit");

            int userDigit = getValidUserInt(7);

            try {
                switch (userDigit) {
                    case 0: {
                        System.out.println("Thank you for using my App. By!");
                        return;
                    }

                    case 1: {
                        System.out.println("Enter a sticker name!");
                        System.out.print("->->-> ");
                        String stickerName = UserLineReader.getUserInputString();
                        System.out.println("Your sticker name is \"" + stickerName + "\"");
                        manager.addSticker(stickerName);
                        continue;
                    }
                    case 2: {
                        System.out.println("Enter a sticker ID!");
                        System.out.print("->->-> ");
                        String stickerID = UserLineReader.getUserInputString();
                        System.out.println("Enter a task name!");
                        System.out.print("->->-> ");
                        String taskName = UserLineReader.getUserInputString();
                        System.out.println("Your sticker ID is \"" + stickerID + "\"");
                        System.out.println("Your task name is \"" + taskName + "\"");
                        manager.addTask(UUID.fromString(stickerID), taskName);
                        continue;
                    }
                    case 3: {
                        manager.showAll();
                        continue;
                    }
                    case 4: {
                        System.out.println("Enter a sticker ID!");
                        System.out.print("->->-> ");
                        String stickerID = UserLineReader.getUserInputString();
                        manager.deleteSticker(UUID.fromString(stickerID));
                        continue;
                    }
                    case 5: {
                        System.out.println("Enter a sticker ID!");
                        System.out.print("->->-> ");
                        String stickerID = UserLineReader.getUserInputString();
                        System.out.println("Enter a task ID!");
                        System.out.print("->->-> ");
                        String taskID = UserLineReader.getUserInputString();
                        System.out.println("Your sticker ID is \"" + stickerID + "\"");
                        System.out.println("Your task ID is \"" + taskID + "\"");
                        manager.deleteTask(UUID.fromString(stickerID), UUID.fromString(taskID));
                        continue;
                    }

                    case 6: {
                        System.out.println("Enter a sticker ID!");
                        System.out.print("->->-> ");
                        String stickerID = UserLineReader.getUserInputString();
                        System.out.println("Enter a task ID!");
                        System.out.print("->->-> ");
                        String taskID = UserLineReader.getUserInputString();
                        manager.changeTaskStatus(UUID.fromString(stickerID), UUID.fromString(taskID));
                        continue;
                    }

                }
            } catch (ObjectNotFound ob) {
                System.out.println(ob + "\n");
            } catch (IllegalArgumentException il) {
                System.out.println("Incorrect ID!\n");
            }
        }


    }

    private static TypeStore startInicializationProcess() {
        TypeStore[] amountOfAvalibleStores = TypeStore.values();

        System.out.println("Chose type of database!(By digit)");
        StoreOptions.showTypeStores();

        int userDigit = getValidUserInt(amountOfAvalibleStores.length);

        // For view type of storage.
        for (int i = 0; i <= amountOfAvalibleStores.length; i++) {
            if (i == userDigit-1) {
                System.out.println(amountOfAvalibleStores[i]);
                return amountOfAvalibleStores[i];
            }
        }

        System.out.println("Will be chosen by default type store is IN_MEMORY");
        return TypeStore.IN_MEMORY;
    }

    private static int getValidUserInt(int maxOptions) {
        int userDigit = 0;
        do {
            try {
                userDigit = UserLineReader.getUserInputDigit(maxOptions+1);
                if (userDigit < 0) throw new BellowZero("The digit zero");
            }catch (NoSuchElementException ne) {
                System.out.println("Invalid input!!!\nEnter a correct number of avalible options!");
            } catch (BellowZero bz) {
                System.out.println(bz.getMessage());
            }
        } while (userDigit < 0 || userDigit > maxOptions);
        return userDigit;

    }


    private static Repository getRepositoryByType(TypeStore typeStore) {
        return switch (typeStore) {
            case IN_MEMORY -> new GlobalRepository();
            //case IN_FILE ->
            //case IN_DATABASE ->
            default -> new GlobalRepository();
        };
    }

}
