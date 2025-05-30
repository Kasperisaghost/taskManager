package org.example.utils;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserLineReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getUserInputDigit(int max) {
        try {
            System.out.print("->->-> ");
            int input = scanner.nextInt(max);
            scanner.nextLine(); // очистити перевід строки після nextInt()
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // очистити неправильний ввід
            throw new NoSuchElementException("Invalid input, expected number.");
        }
    }

    public static String getUserInputString() {
        return scanner.nextLine().strip();
    }
}
