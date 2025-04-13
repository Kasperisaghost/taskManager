package org.example.model.utils;

import org.example.model.exeptions.ObjectNotFound;
import org.example.model.models.Entity;

import java.util.*;

public class IdChecker {

    public static <T extends Entity> T getElementById(Collection<T> data, UUID Id, Class<?> type) throws ObjectNotFound{
        Optional<T> value = data.stream().filter(x -> Objects.equals(x.getId(), Id)).findFirst();
        if (value.isPresent()) return value.get();
        throw new ObjectNotFound("Something going on with that type -> ", type);

    }

    public static <T extends Entity> boolean isIdInCollection(Collection<T> data, UUID Id) {
        return data.stream().anyMatch(x -> Objects.equals(x.getId(), Id));
    }



}
