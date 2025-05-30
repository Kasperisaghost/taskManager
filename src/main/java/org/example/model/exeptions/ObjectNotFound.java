package org.example.model.exeptions;

public class ObjectNotFound extends Exception {
    private Class<?> objectType;
    private String msg;

    public ObjectNotFound(String msg, Class<?> objectType) {
        this.objectType = objectType;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg + " -> " + objectType;
    }
}
