package org.example.utils;

import org.example.model.enums.TypeStore;

import java.util.Arrays;

public class StoreOptions {

    public static void showTypeStores() {

        int counter = 1;
        for (TypeStore typeStore: TypeStore.values()) {
            System.out.println(counter + ". " + typeStore);
            counter++;
        }
    }

}
