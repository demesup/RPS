package org.rps;

import java.io.IOException;

import static org.utils.Read.readPositiveNumber;
import static org.utils.Read.readString;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        start();
    }

    private static void start() {
        try {
            new Game(
                    readString("Enter name"),
                    readPositiveNumber("Enter number of rounds")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}