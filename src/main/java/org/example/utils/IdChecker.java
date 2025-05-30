package org.example.utils;

import org.example.model.exeptions.ObjectNotFound;
import org.example.model.models.Entity;

import java.util.*;

public class IdChecker {

    public static <T extends Entity> T getElementById(Collection<T> data, UUID Id, Class<?> type) throws ObjectNotFound {
        return data.stream()
                .filter(x -> Objects.equals(x.getId(), Id))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFound("Object not found", type));
    }

    public static <T extends Entity> boolean isElementExist(Collection<T> data, UUID Id, Class<?> type) {
        return data.stream()
                .anyMatch(x -> Objects.equals(x.getId(), Id));
    }



}
