package org.example.model.exeptions;

public class BellowZero extends Exception {
    public BellowZero(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
