package org.example;

public class TestUser {
    {
        System.out.println("Empty Block");
    }

    static {
        System.out.println("Statick block");
        Inner.fromInner();
    }

    public TestUser() {
        System.out.println("Basick consructor");
    }

    class Inner {
        private static void fromInner() {
            System.out.println("Call from inner!");
        }
    }
}