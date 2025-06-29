package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TestUser {
    private final Map<String, Integer> INSTANCE = new HashMap<>();

    private TestUser() {}

    public Map<String, Integer> getINSTANCE() {
        return INSTANCE;
    }
}